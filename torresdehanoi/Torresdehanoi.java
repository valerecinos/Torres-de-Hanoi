/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.torresdehanoi;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author valer
 */
public class Torresdehanoi {

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Torres de Hanoi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            frame.getContentPane().add(panel);

            Torresdehanoi torresDeHanoi = new Torresdehanoi(panel);
            frame.setSize(400, 400);
            frame.setVisible(true);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el número de discos: ");
            int numeroDeDiscos = scanner.nextInt();

            torresDeHanoi.inicializarTorres(numeroDeDiscos);
            torresDeHanoi.imprimirEstadoTorres();

            torresDeHanoi.torresdehanoi(numeroDeDiscos, 'A', 'C', 'B');

            System.out.println("\n¡Torres de Hanoi completadas!");
        });
    }

    private final JPanel panel;
    private final char[][] torres;

    public Torresdehanoi(JPanel panel) {
        this.panel = panel;
        this.torres = new char[3][];
    }

    public void inicializarTorres(int n) {
        for (int i = 0; i < 3; i++) {
            torres[i] = new char[n];
        }

        for (int i = 0; i < n; i++) {
            torres[0][i] = (char) (n - i + '0');
        }
    }

    public void torresdehanoi(int n, char origen, char destino, char auxiliar) {
        if (n == 1) {
            moverDisco(origen, destino);
            return;
        }

        torresdehanoi(n - 1, origen, auxiliar, destino);
        moverDisco(origen, destino);
        torresdehanoi(n - 1, auxiliar, destino, origen);
    }

    private void moverDisco(char origen, char destino) {
        int disco = obtenerDiscoSuperior(origen);
    quitarDisco(origen);
    colocarDisco(destino, disco);
    imprimirMovimientoDisco(disco, origen, destino);
    imprimirEstadoTorres();
    }
    
    private void imprimirMovimientoDisco(int disco, char origen, char destino) {
    System.out.println("Mover disco " + disco + " desde la varilla " + origen + " a la varilla " + destino);
}

    private void imprimirEstadoTorres() {
        panel.removeAll();

        for (int i = torres.length - 1; i >= 0; i--) {
            JPanel varillaPanel = new JPanel();
            varillaPanel.setLayout(new BoxLayout(varillaPanel, BoxLayout.X_AXIS));
            varillaPanel.add(new JLabel("Varilla " + (char) ('A' + i) + ": "));

            for (int j = 0; j < torres[i].length; j++) {
                if (torres[i][j] != 0) {
                    int disco = torres[i][j] - '0';
                    varillaPanel.add(new DiscoLabel(disco));
                } else {
                    varillaPanel.add(new JLabel("| "));
                }
            }

            panel.add(varillaPanel);
        }

        panel.revalidate();
        panel.repaint();
    }

    private int obtenerDiscoSuperior(char varilla) {
        for (int i = 0; i < torres.length; i++) {
            if (torres[i][torres[i].length - 1] != 0 && torres[i][torres[i].length - 1] == varilla) {
                return torres[i][torres[i].length - 1] - '0';
            }
        }
        return -1;
    }

    private void quitarDisco(char varilla) {
        for (int i = 0; i < torres.length; i++) {
            if (torres[i][torres[i].length - 1] != 0 && torres[i][torres[i].length - 1] == varilla) {
                torres[i][torres[i].length - 1] = 0;
                return;
            }
        }
    }

    private void colocarDisco(char varilla, int disco) {
        for (int i = 0; i < torres.length; i++) {
            if (torres[i][0] == 0 || torres[i][0] == varilla) {
                torres[i][0] = (char) (disco + '0');
                return;
            }
        }
    }

    private static class DiscoLabel extends JLabel {
        public DiscoLabel(int size) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < size; i++) {
                stringBuilder.append("*");
            }
            setText(stringBuilder.toString() + " ");
        }
    }
}
