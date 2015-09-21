package Entities;

/**
 * Created by Computer on 20.09.2015.
 */
public interface MoveableEntity {
    RealEntity getRealEntity();

    RealEntity getOriginal();

    void setOriginal(RealEntity original);
}
