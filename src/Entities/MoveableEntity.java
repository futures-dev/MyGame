package Entities;

/**
 * Created by Computer on 20.09.2015.
 */
public interface MoveableEntity {
    RealEntity getRealEntity();
    void setOriginal(RealEntity original);
    RealEntity getOriginal();
}
