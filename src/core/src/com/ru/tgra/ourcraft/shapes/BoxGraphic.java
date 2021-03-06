package com.ru.tgra.ourcraft.shapes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;
import com.ru.tgra.ourcraft.Shader;
import com.ru.tgra.ourcraft.models.CubeMask;

import java.nio.FloatBuffer;

public class BoxGraphic {

	private static FloatBuffer vertexBuffer;
	private static FloatBuffer normalBuffer;
	private static FloatBuffer invertedNormalBuffer;
    //private static FloatBuffer uvBuffer;

	public static void create() {

		//VERTEX ARRAY IS FILLED HERE
		float[] vertexArray = {-0.5f, -0.5f, -0.5f,
						-0.5f, 0.5f, -0.5f,
						0.5f, 0.5f, -0.5f,
						0.5f, -0.5f, -0.5f,
						-0.5f, -0.5f, 0.5f,
						-0.5f, 0.5f, 0.5f,
						0.5f, 0.5f, 0.5f,
						0.5f, -0.5f, 0.5f,
						-0.5f, -0.5f, -0.5f,
						0.5f, -0.5f, -0.5f,
						0.5f, -0.5f, 0.5f,
						-0.5f, -0.5f, 0.5f,
						-0.5f, 0.5f, -0.5f,
						0.5f, 0.5f, -0.5f,
						0.5f, 0.5f, 0.5f,
						-0.5f, 0.5f, 0.5f,
						-0.5f, -0.5f, -0.5f,
						-0.5f, -0.5f, 0.5f,
						-0.5f, 0.5f, 0.5f,
						-0.5f, 0.5f, -0.5f,
						0.5f, -0.5f, -0.5f,
						0.5f, -0.5f, 0.5f,
						0.5f, 0.5f, 0.5f,
						0.5f, 0.5f, -0.5f};

		vertexBuffer = BufferUtils.newFloatBuffer(72);
		vertexBuffer.put(vertexArray);
		vertexBuffer.rewind();

		//NORMAL ARRAY IS FILLED HERE
		float[] normalArray = {0.0f, 0.0f, -1.0f,
							0.0f, 0.0f, -1.0f,
							0.0f, 0.0f, -1.0f,
							0.0f, 0.0f, -1.0f,
							0.0f, 0.0f, 1.0f,
							0.0f, 0.0f, 1.0f,
							0.0f, 0.0f, 1.0f,
							0.0f, 0.0f, 1.0f,
							0.0f, -1.0f, 0.0f,
							0.0f, -1.0f, 0.0f,
							0.0f, -1.0f, 0.0f,
							0.0f, -1.0f, 0.0f,
							0.0f, 1.0f, 0.0f,
							0.0f, 1.0f, 0.0f,
							0.0f, 1.0f, 0.0f,
							0.0f, 1.0f, 0.0f,
							-1.0f, 0.0f, 0.0f,
							-1.0f, 0.0f, 0.0f,
							-1.0f, 0.0f, 0.0f,
							-1.0f, 0.0f, 0.0f,
							1.0f, 0.0f, 0.0f,
							1.0f, 0.0f, 0.0f,
							1.0f, 0.0f, 0.0f,
							1.0f, 0.0f, 0.0f};


		normalBuffer = BufferUtils.newFloatBuffer(72);
		normalBuffer.put(normalArray);
		normalBuffer.rewind();

        // INVERTED NORMAL ARRAY IS FILLED HERE
        for (int i = 0; i < normalArray.length; i++)
        {
            normalArray[i] = -1 * normalArray[i];
        }

        invertedNormalBuffer = BufferUtils.newFloatBuffer(72);
        invertedNormalBuffer.put(normalArray);
        invertedNormalBuffer.rewind();

//        //UV TEXTURE COORD ARRAY IS FILLED HERE
//        float[] uvArray =
//        {
//            0.0f, 0.0f,
//            1.0f, 0.0f,
//            1.0f, 1.0f,
//            0.0f, 1.0f,
//
//            0.0f, 0.0f,
//            1.0f, 0.0f,
//            1.0f, 1.0f,
//            0.0f, 1.0f,
//
//            0.0f, 0.0f,
//            1.0f, 0.0f,
//            1.0f, 1.0f,
//            0.0f, 1.0f,
//
//            0.0f, 0.0f,
//            1.0f, 0.0f,
//            1.0f, 1.0f,
//            0.0f, 1.0f,
//
//            0.0f, 0.0f,
//            1.0f, 0.0f,
//            1.0f, 1.0f,
//            0.0f, 1.0f,
//
//            0.0f, 0.0f,
//            1.0f, 0.0f,
//            1.0f, 1.0f,
//            0.0f, 1.0f,
//        };
//
//        uvBuffer = BufferUtils.newFloatBuffer(48);
//        BufferUtils.copy(uvArray, 0, uvBuffer, 48);
//        uvBuffer.rewind();
	}

    public static void drawSolidCube(Shader shader, Texture diffuseTexture, FloatBuffer uvBuffer, CubeMask mask, boolean invertedNormal)
    {
    	if (mask.isInvisible())
		{
			return;
		}

        shader.setDiffuseTexture(diffuseTexture);

        Gdx.gl.glVertexAttribPointer(shader.getVertexPointer(), 3, GL20.GL_FLOAT, false, 0, vertexBuffer);

        if (invertedNormal)
        {
            Gdx.gl.glVertexAttribPointer(shader.getNormalPointer(), 3, GL20.GL_FLOAT, false, 0, invertedNormalBuffer);
        }
        else
        {
            Gdx.gl.glVertexAttribPointer(shader.getNormalPointer(), 3, GL20.GL_FLOAT, false, 0, normalBuffer);
        }

        if (uvBuffer != null)
		{
			Gdx.gl.glVertexAttribPointer(shader.getUVPointer(), 2, GL20.GL_FLOAT, false, 0, uvBuffer);
		}

        // North
        if (mask.isWest())
        {
            Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 0, 4);
        }

        // South
        if (mask.isEast())
        {
            Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 4, 4);
        }

        // Bottom
        if (mask.isBottom())
        {
            Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 8, 4);
        }

        // Top
        if (mask.isTop())
        {
            Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 12, 4);
        }

        // West
        if (mask.isSouth())
        {
            Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 16, 4);
        }

        // East
        if (mask.isNorth())
        {
            Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 20, 4);
        }
    }

	public static void drawOutlineCube(Shader shader, CubeMask mask)
    {

		Gdx.gl.glVertexAttribPointer(shader.getVertexPointer(), 3, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glVertexAttribPointer(shader.getNormalPointer(), 3, GL20.GL_FLOAT, false, 0, normalBuffer);

		Gdx.gl.glLineWidth(4);

        // North
        if (mask.isWest())
        {
            Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 0, 4);
        }

        // South
        if (mask.isEast())
        {
            Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 4, 4);
        }

        // Bottom
        if (mask.isBottom())
        {
            Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 8, 4);
        }

        // Top
        if (mask.isTop())
        {
            Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 12, 4);
        }

        // West
        if (mask.isSouth())
        {
            Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 16, 4);
        }

        // East
        if (mask.isNorth())
        {
            Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 20, 4);
        }
	}

}
