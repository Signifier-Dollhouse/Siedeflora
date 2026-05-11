package com.signifier.siedeflora.agriculture.interact;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

import java.util.Arrays;

/**
 * Oriented Bounding Box with only roll and yaw
 */
public class RYBB {
    public static final double EPSILON = 1.0E-7;

    public static final Codec<RYBB> CODEC = RecordCodecBuilder.create(ins -> ins.group(
            V3dUtil.CODEC.fieldOf("center").forGetter(RYBB::center),
            V3dUtil.CODEC.fieldOf("extent").forGetter(RYBB::extent),
            Codec.FLOAT.fieldOf("roll").forGetter(RYBB::roll),
            Codec.FLOAT.fieldOf("yaw").forGetter(RYBB::yaw)
    ).apply(ins, RYBB::new));

    // Constants, avoid outer modification
    private static final Vector3d X_AXIS = new Vector3d(1, 0, 0);
    private static final Vector3d Y_AXIS = new Vector3d(0, 1, 0);
    private static final Vector3d Z_AXIS = new Vector3d(0, 0, 1);

    // RYBB Properties
    private final Vector3d center;
    private final Vector3d extent;

    private final Vector3d xAxis;
    private final Vector3d yAxis;
    private final Vector3d zAxis = new Vector3d();

    // don't store yaw in local Coordinates
    // instead, rotate the vertices of aabb on each calculation
    private final float roll, yaw;

    private final Vector3d offset = new Vector3d();
    private float aabbYaw;

    // Calculation buffers
    private final Vector3d X_Axis;
    private final Vector3d Z_Axis;
    private final Vector3d[] vertices = new Vector3d[8];
    private final Vector3d[] aabbVertices = new Vector3d[9];

    // Right hand Coordinate, rotateZYX (roll, yaw, pitch)
    public RYBB(Vector3d center, Vector3d extent, float roll, float yaw) {
        this.center = center;
        this.extent = extent;
        this.roll = roll;
        this.xAxis = new Vector3d(X_AXIS).rotateZ(roll).normalize();
        this.yAxis = new Vector3d(Y_AXIS).rotateZ(roll).normalize();
        this.xAxis.cross(this.yAxis, this.zAxis).normalize();
        this.yaw = yaw;
        this.X_Axis = new Vector3d(X_AXIS);
        this.Z_Axis = new Vector3d(Z_AXIS);

        Arrays.setAll(this.vertices, index -> new Vector3d());
        Arrays.setAll(this.aabbVertices, index -> new Vector3d());

        this.updateVertices();
    }

    // Codec field getter
    private Vector3d center() {
        return center;
    }

    private Vector3d extent() {
        return extent;
    }

    public float roll() {
        return roll;
    }

    private float yaw() {
        return yaw;
    }

    // Position & Rotation follower
    public RYBB moveTo(double x, double y, double z) {
        this.offset.set(x, y, z);
        return this;
    }

    public RYBB moveTo(Vec3 dest) {
        return this.moveTo(dest.x(), dest.y(), dest.z());
    }

    public RYBB faceTo(float rotation) {
        this.aabbYaw = -this.yaw - rotation;
        X_AXIS.rotateY(this.aabbYaw, this.X_Axis);
        Z_AXIS.rotateY(this.aabbYaw, this.Z_Axis);
        return this;
    }

//    // Reposition & Resize
//    // Note: RYBB is mostly immutable, modifications are not recommended
//    public RYBB centerTo(double x, double y, double z) {
//        this.center.set(x, y, z);
//        this.changed = true;
//        return this;
//    }
//
//    public RYBB extentTo(double xMeasure, double yMeasure, double zMeasure) {
//        this.extent.set(xMeasure / 2, yMeasure / 2, zMeasure / 2);
//        this.changed = true;
//        return this;
//    }
//
//    public RYBB rotateTo(float roll, float yaw) {
//        this.roll = roll;
//        this.yaw = yaw;
//        this.xAxis = new Vector3d(X_AXIS).rotateZ(roll).normalize();
//        this.yAxis = new Vector3d(Y_AXIS).rotateZ(roll).normalize();
//        this.xAxis.cross(this.yAxis, this.zAxis).normalize();
//        this.changed = true;
//        return this;
//    }

    // Collision detection
    public boolean contains(Vector3d point) {
        return contains(point, false);
    }

    private boolean contains(Vector3d point, boolean localCoord) {
        if (!localCoord) {
            point = point.sub(this.center, new Vector3d());
            point.sub(this.offset);
            point.rotateY(this.aabbYaw);
        }

        return Math.abs(point.dot(this.xAxis)) <= this.extent.x
               && Math.abs(point.dot(this.yAxis)) <= this.extent.y
               && Math.abs(point.dot(this.zAxis)) <= this.extent.z;
    }

    public boolean intersects(AABB aabb) {
        this.updateAABBVertices(aabb);

        if (this.contains(this.aabbVertices[8], true))
            return true;

        if (projectionSeparates(this.X_Axis)) return false;
        if (projectionSeparates(Y_AXIS)) return false;
        if (projectionSeparates(this.Z_Axis)) return false;

        // Two AABB condition
        if (!this.hasRoll()) return true;

        if (projectionSeparates(this.xAxis)) return false;
        if (projectionSeparates(this.yAxis)) return false;
        // if the OBB only has roll, no extra detections are needed
        if (!this.hasYaw()) return true;
        if (projectionSeparates(this.zAxis)) return false;

        // since we have no pitch, some detections are ignored
        if (projectionSeparates(this.X_Axis.cross(this.xAxis, new Vector3d()))) return false;
        if (projectionSeparates(this.X_Axis.cross(this.yAxis, new Vector3d()))) return false;
        // parallel with Y_AXIS
        // if (projectionSeparates(X_AXIS.cross(this.zAxis))) return false;


        // parallel with this.xAxis
        // if (projectionSeparates(Y_AXIS.cross(this.xAxis))) return false;
        // projectionSeparates(vertices, Y_AXIS.cross(this.yAxis)) -sin(β)(cos(α), 0, sin(α))
        if (projectionSeparates(Y_AXIS.cross(this.zAxis, new Vector3d()))) return false;

        if (projectionSeparates(this.Z_Axis.cross(this.xAxis, new Vector3d()))) return false;
        if (projectionSeparates(this.Z_Axis.cross(this.yAxis, new Vector3d()))) return false;
        // parallel with Y_AXIS
        // if (projectionSeparates(Z_AXIS.cross(this.zAxis))) return false;

        return true;
    }

    /**
     * Segment-OBB intersection in world space.
     */
    public boolean intersectsRay(Vector3d from, Vector3d to) {
        // Transform both endpoints into the same local frame used by SAT.
        Vector3d localFrom = this.toLocalPoint(from, new Vector3d());
        Vector3d localTo = this.toLocalPoint(to, new Vector3d());
        Vector3d localDir = localTo.sub(localFrom, new Vector3d());

        // Clip ray to each axis slab
        double[] tInterval = {0.0, 1.0};

        if (!clipRayToAxis(localFrom, localDir, this.xAxis, this.extent.x, tInterval)) return false;
        if (!clipRayToAxis(localFrom, localDir, this.yAxis, this.extent.y, tInterval)) return false;
        if (!clipRayToAxis(localFrom, localDir, this.zAxis, this.extent.z, tInterval)) return false;

        return true;
    }

    /**
     * Clip ray segment [tInterval[0], tInterval[1]] to a slab defined by the given axis and extent.
     * Returns false if no intersection, true otherwise and updates tInterval.
     */
    private boolean clipRayToAxis(Vector3d localFrom, Vector3d localDir, Vector3d axis, double extent, double[] tInterval) {
        double origin = localFrom.dot(axis);
        double direction = localDir.dot(axis);

        if (Math.abs(direction) < EPSILON) {
            // Ray is parallel to slab plane
            return origin >= -extent && origin <= extent;
        }

        // Compute t where ray pierces the two slab planes
        double t1 = (-extent - origin) / direction;
        double t2 = (extent - origin) / direction;
        if (t1 > t2) {
            double tmp = t1;
            t1 = t2;
            t2 = tmp;
        }

        // Clip segment to this slab's intersection interval
        tInterval[0] = Math.max(tInterval[0], t1);
        tInterval[1] = Math.min(tInterval[1], t2);

        return tInterval[0] <= tInterval[1];
    }

    // Local helper methods
    private boolean hasRoll() {
        return this.roll < -EPSILON || this.roll > EPSILON;
    }

    private boolean hasYaw() {
        return this.aabbYaw < -EPSILON || this.aabbYaw > EPSILON;
    }

    private Vector3d toLocalPoint(Vector3d worldPoint, Vector3d dest) {
        // Translate to box origin, then cancel runtime yaw.
        return dest.set(worldPoint)
                .sub(this.center)
                .sub(this.offset)
                .rotateY(this.aabbYaw);
    }

    private void updateVertices() {
        boolean fx, fy, fz;
        double x, y, z;
        for (int i = 0; i < 8; i++) {
            fx = (i & 1) == 0;
            fy = (i >> 1 & 1) == 0;
            fz = (i >> 2 & 1) == 0;
            x = (fx ? xAxis.x : -xAxis.x) * extent.x +
                (fy ? yAxis.x : -yAxis.x) * extent.y +
                (fz ? zAxis.x : -zAxis.x) * extent.z;
            y = (fx ? xAxis.y : -xAxis.y) * extent.x +
                (fy ? yAxis.y : -yAxis.y) * extent.y +
                (fz ? zAxis.y : -zAxis.y) * extent.z;
            z = (fx ? xAxis.z : -xAxis.z) * extent.x +
                (fy ? yAxis.z : -yAxis.z) * extent.y +
                (fz ? zAxis.z : -zAxis.z) * extent.z;
            // do I need give up new?
            this.vertices[i].set(x, y, z);
//                this.vertices[i] = xAxis.mul((i & 1) == 0 ? extent.x : -extent.x)
//                        .add(yAxis.scale((i >> 1 & 1) == 0 ? extent.y : -extent.y))
//                        .add(zAxis.scale((i >> 2 & 1) == 0 ? extent.z : -extent.z));
        }
    }

    private void updateAABBVertices(AABB aabb) {
        double x, y, z;
        for (int i = 0; i < 8; i++) {
            x = ((i & 1) == 0 ? aabb.maxX : aabb.minX) - center.x - offset.x;
            y = ((i >> 1 & 1) == 0 ? aabb.maxY : aabb.minY) - center.y - offset.y;
            z = ((i >> 2 & 1) == 0 ? aabb.maxZ : aabb.minZ) - center.z - offset.z;
            this.aabbVertices[i].set(x, y, z).rotateY(this.aabbYaw);
        }
        this.aabbVertices[8].set(this.aabbVertices[0]).add(this.aabbVertices[7]).div(2);
    }

    // Detect the OBB's projection will intersect on axis
    private boolean projectionSeparates(Vector3d axis) {
        if (axis.length() < 1.73 * EPSILON) return false;

        double sMax = this.vertices[0].dot(axis);
        double sMin = this.vertices[0].dot(axis);
        double vMax = this.aabbVertices[0].dot(axis);
        double vMin = this.aabbVertices[0].dot(axis);

        for (int i = 1; i < 8; i++) {
            double sVal = this.vertices[i].dot(axis);
            if (sVal < sMin) sMin = sVal;
            if (sVal > sMax) sMax = sVal;
            double vVal = this.aabbVertices[i].dot(axis);
            if (vVal < vMin) vMin = vVal;
            if (vVal > vMax) vMax = vVal;
        }

        return sMax < vMin || sMin > vMax;
    }

    public static class V3dUtil {
        public static final Codec<Vector3d> CODEC = Vec3.CODEC.xmap(V3dUtil::to, V3dUtil::from);

        private static Vector3d to(Vec3 vec3) {
            return new Vector3d(vec3.x, vec3.y, vec3.z);
        }

        private static Vec3 from(Vector3d vec3) {
            return new Vec3(vec3.x, vec3.y, vec3.z);
        }
    }
}
