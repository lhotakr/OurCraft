package com.ru.tgra.ourcraft.objects;

import com.ru.tgra.ourcraft.Camera;
import com.ru.tgra.ourcraft.GameManager;
import com.ru.tgra.ourcraft.GraphicsEnvironment;
import com.ru.tgra.ourcraft.Settings;
import com.ru.tgra.ourcraft.models.Material;
import com.ru.tgra.ourcraft.models.ModelMatrix;
import com.ru.tgra.ourcraft.models.Point3D;
import com.ru.tgra.ourcraft.models.Vector3D;
import com.ru.tgra.ourcraft.shapes.SphereGraphic;

public class Player extends GameObject
{
    private Camera camera;

    private float radius;
    private float offset = 0.5f;

    private Vector3D movementVector;
    private float yaw;
    private float pitch;

    public Player(Point3D position, Vector3D scale, float speed, Material material)
    {
        super();

        this.position = position;
        this.scale = scale;
        this.speed = speed;
        this.material = material;

        radius = 0.25f;

        Point3D center = new Point3D(position);
        center.z += 1;

        camera = new Camera();
        camera.look(position, center, new Vector3D(0,1,0));

        movementVector = new Vector3D();
        yaw = 0;
        pitch = 0f;
    }

    public void update(float deltaTime)
    {
        movementVector.scale(deltaTime);
        yaw *= deltaTime;
        pitch *= deltaTime;

        camera.slide(movementVector.x, movementVector.y, movementVector.z);
        camera.yaw(yaw);
        camera.pitch(pitch);

        movementVector.set(0, 0, 0);
        yaw = 0;
        pitch = 0;

        int x = (int) position.x;
        int y = (int) position.y;
        int z = (int) position.z;

        System.out.print("\r                                 \r");
        try
        {
            boolean inside = GameManager.worldBlocks[x][y][z];
            System.out.print("x: " + x + ", y:" + y + ", z:" + z + " | In block: " + inside);
        }
        catch (Exception ex)
        {
            System.out.print("Out of bounds");
        }

    }

    public void draw(int viewportID)
    {
        if (viewportID == Settings.viewportIDPerspective)
        {
            return;
        }

        ModelMatrix.main.loadIdentityMatrix();
        ModelMatrix.main.addTranslation(position);
        ModelMatrix.main.addScale(scale);

        GraphicsEnvironment.shader.setModelMatrix(ModelMatrix.main.getMatrix());
        GraphicsEnvironment.shader.setMaterial(material);

        SphereGraphic.drawSolidPolySphere();
    }

    public void moveLeft()
    {
        movementVector.x = -Settings.playerSpeed;
    }

    public void moveRight()
    {
        movementVector.x = Settings.playerSpeed;
    }

    public void moveForward()
    {
        movementVector.z = -Settings.playerSpeed;
    }

    public void moveBack()
    {
        movementVector.z = Settings.playerSpeed;
    }

    public void lookLeft()
    {
        yaw = Settings.playerButtonLookSensitivity;
    }

    public void lookRight()
    {
        yaw = -Settings.playerButtonLookSensitivity;
    }

    public void lookUp()
    {
        pitch = Settings.playerButtonLookSensitivity;
    }

    public void lookDown()
    {
        pitch = -Settings.playerButtonLookSensitivity;
    }

    public void mouseLookYaw(float amount)
    {
        yaw = amount * Settings.playerMouseLookSensitivity;
    }

    public void mouseLookPitch(float amount)
    {
        pitch = amount * Settings.playerMouseLookSensitivity;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public int getMazeX()
    {
        return (int) (position.x + 0.5f);
    }

    public int getMazeY()
    {
        return (int) (position.z + 0.5f);
    }

    public float getRadius()
    {
        return radius;
    }
}
