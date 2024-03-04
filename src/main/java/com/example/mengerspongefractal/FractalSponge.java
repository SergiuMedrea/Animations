package com.example.mengerspongefractal;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class FractalSponge extends Application {
    private static final int SIZE = 180;
    private static int divisions = 3;
    private Group root;

    @Override
    public void start(Stage stage) {
        root = new Group();

        addMengerSponge(0, 0, 0, SIZE);

        Scene scene = new Scene(root, 1200, 1000);
        scene.setFill(Color.BLACK);

        // Handle mouse click event to subdivide the cube
        scene.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                addMengerSponge(0, 0, 0, SIZE / divisions);
                divisions *= 3;
            }
        });

        Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
        root.getTransforms().addAll(rotateX, rotateY);

        root.setTranslateX(scene.getWidth() / 2 - SIZE / 3);
        root.setTranslateY(scene.getHeight() / 2 - SIZE / 3);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                rotateX.setAngle(rotateX.getAngle() + 0.1);
                rotateY.setAngle(rotateY.getAngle() + 0.1);
            }
        };
        animationTimer.start();

        stage.setTitle("Rotating Cube");
        stage.setScene(scene);
        stage.show();
    }

    private void addMengerSponge(double x, double y, double z, double size) {
        if (size < 1) {
            return;
        }

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    // Exclude the center cube
                    if (Math.abs(dx) + Math.abs(dy) + Math.abs(dz) > 1) {
                        addCube(x + dx * size, y + dy * size, z + dz * size, size);
                    }
                }
            }
        }
    }

    private void addCube(double x, double y, double z, double size) {
        Box cube = new Box(size, size, size);
        cube.setTranslateX(x);
        cube.setTranslateY(y);
        cube.setTranslateZ(z);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.WHITE);
        cube.setMaterial(material);
        root.getChildren().add(cube);
    }

    public static void main(String[] args) {
        launch();
    }
}