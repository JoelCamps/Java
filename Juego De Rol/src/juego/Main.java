package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel {
    private JPanel panelMain, panelSeleccion, panelMapa, panelInventario;
    private JButton botonMago, botonGuerrero, botonSacerdote;
    private JLabel labelPJ, labelVidas, labelOro, labelTiempo, labelEspada, labelMitra, labelPocion, labelEnemigoV, labelEnemigoH, labelMuro;
    private JLabel labelContadorOro, labelContadorEspada, labelContadorMitra, labelContadorPocion, labelTipoPJ;
    private String nombre;
    private int vidas, oro, espada, mitra, pocion, segundos;
    private Timer temporizador;
    private ArrayList<JLabel> enemigosH = new ArrayList<>(), enemigosV = new ArrayList<>(), enemigos = new ArrayList<>();
    private Rectangle colisionEnemigoH, colisionEnemigoV, colisionEspada, colisionPocion, colisionMitra, colisionOro, colisionPJ;

    public Main() {
        panelMain.setPreferredSize(new Dimension(800, 640));
        panelMain.setSize(new Dimension(800, 640));
        panelMain.setLayout(null);
        panelMain.setFocusable(true);

        nombre = JOptionPane.showInputDialog(null, "Introduce el nombre que quieras darle a tu personaje:");
        mostrarPanelSeleccion();
        seleccionaPersonaje();
    }

    private void fin() {
        if (oro == 50 && labelPJ.getX() >= 730 && labelPJ.getY() >= 505){
            JOptionPane.showMessageDialog(null, "Has ganado, felizidades!");
            System.exit(0);
        }
        if (vidas == 0){
            JOptionPane.showMessageDialog(null, "Ya me joderia");
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarMapa();
    }

    private void seleccionaPersonaje() {
        //Dibujar mapa y personaje
        ActionListener boton = e -> {
            dibujarMapa();

            labelPJ = new JLabel();
            labelPJ.setSize(64, 64);
            labelPJ.setLocation(20, labelPJ.getHeight()/2);

            labelTipoPJ = new JLabel();

            if (e.getSource() == botonMago) {
                ImageIcon mago = new ImageIcon("src/images/wizard/wizard_down.gif");
                Icon iconoMago = new ImageIcon(
                        mago.getImage().getScaledInstance(labelPJ.getWidth(), labelPJ.getHeight(), Image.SCALE_DEFAULT)
                );
                labelPJ.setIcon(iconoMago);
                labelTipoPJ.setText("Mago");

                panelMapa.addKeyListener(new movimientoMago());
                vidas = 3;
            } else if (e.getSource() == botonGuerrero) {
                ImageIcon guerrero = new ImageIcon("src/images/warrior/warrior_down.gif");
                Icon iconoGuerrero = new ImageIcon(
                        guerrero.getImage().getScaledInstance(labelPJ.getWidth(), labelPJ.getHeight(), Image.SCALE_DEFAULT)
                );
                labelPJ.setIcon(iconoGuerrero);
                labelTipoPJ.setText("Guerrero");

                panelMapa.addKeyListener(new movimientoGuerrero());
                vidas = 5;
            } else {
                ImageIcon sacerdote = new ImageIcon("src/images/priest/priest_down.gif");
                Icon iconoSacerdote = new ImageIcon(
                        sacerdote.getImage().getScaledInstance(labelPJ.getWidth(), labelPJ.getHeight(), Image.SCALE_DEFAULT)
                );
                labelPJ.setIcon(iconoSacerdote);
                labelTipoPJ.setText("Sacerdote");

                panelMapa.addKeyListener(new movimientoSacerdote());
                vidas = 4;
            }
            mostrarPanelInventario();

            panelMapa.grabFocus();
            panelMapa.add(labelPJ);
            panelMapa.setComponentZOrder(labelPJ, 0);
            panelMapa.repaint();

            panelMapa.requestFocusInWindow();
        };
        botonMago.addActionListener(boton);
        botonGuerrero.addActionListener(boton);
        botonSacerdote.addActionListener(boton);
    }

    private class movimientoSacerdote extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int x = labelPJ.getX(), y = labelPJ.getY();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> {
                    x += 5;
                    actualizarImagen("src/images/priest/priest_right.gif");
                }
                case KeyEvent.VK_LEFT -> {
                    x -= 5;
                    actualizarImagen("src/images/priest/priest_left.gif");
                }
                case KeyEvent.VK_DOWN -> {
                    y += 5;
                    actualizarImagen("src/images/priest/priest_down.gif");
                }
                case KeyEvent.VK_UP -> {
                    y -= 5;
                    actualizarImagen("src/images/priest/priest_up.gif");
                }
            }
            if (x >= 20 && x <= panelMapa.getWidth() - labelPJ.getWidth() && y >= 0 && y <= panelMapa.getHeight() - labelPJ.getHeight()) {
                labelPJ.setLocation(x, y);
                fin();
            }
        }
    }

    private class movimientoGuerrero extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int x = labelPJ.getX(), y = labelPJ.getY();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> {
                    x += 3;
                    actualizarImagen("src/images/warrior/warrior_right.gif");
                }
                case KeyEvent.VK_LEFT -> {
                    x -= 3;
                    actualizarImagen("src/images/warrior/warrior_left.gif");
                }
                case KeyEvent.VK_DOWN -> {
                    y += 3;
                    actualizarImagen("src/images/warrior/warrior_down.gif");
                }
                case KeyEvent.VK_UP -> {
                    y -= 3;
                    actualizarImagen("src/images/warrior/warrior_up.gif");
                }
            }
            if (x >= 20 && x <= panelMapa.getWidth() - labelPJ.getWidth() && y >= 0 && y <= panelMapa.getHeight() - labelPJ.getHeight()) {
                labelPJ.setLocation(x, y);
                fin();
            }
        }
    }

    private class movimientoMago extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int x = labelPJ.getX(), y = labelPJ.getY();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT -> {
                    x += 7;
                    actualizarImagen("src/images/wizard/wizard_right.gif");
                }
                case KeyEvent.VK_LEFT -> {
                    x -= 7;
                    actualizarImagen("src/images/wizard/wizard_left.gif");
                }
                case KeyEvent.VK_DOWN -> {
                    y += 7;
                    actualizarImagen("src/images/wizard/wizard_down.gif");
                }
                case KeyEvent.VK_UP -> {
                    y -= 7;
                    actualizarImagen("src/images/wizard/wizard_up.gif");
                }
            }
            if (x >= 20 && x <= panelMapa.getWidth() - labelPJ.getWidth() && y >= 0 && y <= panelMapa.getHeight() - labelPJ.getHeight()) {
                labelPJ.setLocation(x, y);
                fin();
            }
        }
    }

    private void actualizarImagen(String ruta) {
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(
                imagen.getImage().getScaledInstance(labelPJ.getWidth(), labelPJ.getHeight(), Image.SCALE_DEFAULT)
        );
        labelPJ.setIcon(icono);
    }

    private void mostrarPanelInventario() {
        panelInventario = new JPanel();
        panelInventario.setLocation(0, 0);
        panelInventario.setSize(panelSeleccion.getSize());
        panelInventario.setBackground(Color.lightGray);

        panelMain.add(panelInventario);

        //Label usuariop
        JLabel labelUsuario = new JLabel();
        labelUsuario.setText("Nombre: " + nombre);

        panelInventario.add(labelUsuario);

        //Label vidas
        labelVidas = new JLabel();
        labelVidas.setText("Vidas: " + vidas);
        panelInventario.add(labelVidas);

        //Label oro
        labelContadorOro = new JLabel();
        labelContadorOro.setText("Oro: " + oro);
        panelInventario.add(labelContadorOro);

        //Label espada
        labelContadorEspada = new JLabel();
        labelContadorEspada.setText("Espada: " + espada + "/1");

        panelInventario.add(labelContadorEspada);

        //Label pocion
        labelContadorPocion = new JLabel();
        labelContadorPocion.setText("Pocion: " + pocion + "/1");

        panelInventario.add(labelContadorPocion);

        //Label mitra
        labelContadorMitra = new JLabel();
        labelContadorMitra.setText("Mitra: " + mitra + "/1");

        panelInventario.add(labelContadorMitra);

        //Label tiempo con timer
        labelTiempo = new JLabel();
        panelInventario.add(labelTiempo);

        temporizador = new Timer(1000, new temporizador());
        temporizador.start();
    }
    private class temporizador implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            segundos++;
            labelTiempo.setText(segundos + " segundos");
        }
    }
    private void dibujarMapa() {
        panelMain.remove(panelSeleccion);
        panelMain.repaint();

        panelMapa = new JPanel();
        panelMapa.setLayout(null);
        panelMapa.setLocation(0, panelSeleccion.getHeight());
        panelMapa.setSize(panelMain.getWidth(), panelMain.getHeight() - panelSeleccion.getHeight());
        panelMapa.setLayout(null);

        panelMain.add(panelMapa);

        //Dibujo muros izquierda y derecha
        labelMuro = new JLabel();
        labelMuro.setSize(32, 32);
        ImageIcon muro = new ImageIcon("src/images/dungeon/muro.png");
        Icon iconoMuro = new ImageIcon(
                muro.getImage().getScaledInstance(labelMuro.getWidth(), labelMuro.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMuro.setIcon(iconoMuro);

        int y = 0, x = 0, contador = 0;
        while (y < panelMapa.getHeight()) {
            if (contador != 1 && contador != 2) {
                JLabel muroIzquierda = new JLabel(iconoMuro);
                muroIzquierda.setSize(32, 32);
                muroIzquierda.setLocation(0, y);

                panelMapa.add(muroIzquierda);
            }
            if (contador != panelMapa.getHeight() / labelMuro.getHeight() - 2 && contador != panelMapa.getHeight() / labelMuro.getHeight() - 3) {
                JLabel muroDerecha = new JLabel(iconoMuro);
                muroDerecha.setSize(32, 32);
                muroDerecha.setLocation(panelMapa.getWidth() - muroDerecha.getWidth(), y);

                panelMapa.add(muroDerecha);
            }
            y += labelMuro.getHeight();
            contador++;
        }

        //Dibujos muros arriba y abajo
        while (x < panelMapa.getWidth()) {
            JLabel muroArriba = new JLabel(iconoMuro);
            muroArriba.setSize(32, 32);
            muroArriba.setLocation(x, 0);

            panelMapa.add(muroArriba);

            y = panelMapa.getHeight() - muroArriba.getHeight();

            JLabel muroAbajo = new JLabel(iconoMuro);
            muroAbajo.setSize(32, 32);
            muroAbajo.setLocation(x, y);

            panelMapa.add(muroAbajo);

            x += labelMuro.getWidth();
        }

        //Dibujo del suelo
        for (int fila = 1; fila <= 2; fila++) {
            dibujarSuelo(fila, labelMuro);
        }
        for (int fila = 3; fila <= panelMapa.getHeight() / labelMuro.getHeight() - 4; fila++) {
            dibujarSuelo(fila, labelMuro);
        }
        for (int fila = panelMapa.getHeight() / labelMuro.getHeight() - 3; fila <= panelMapa.getHeight() / labelMuro.getHeight() - 1; fila++) {
            dibujarSuelo(fila, labelMuro);
        }

        //Generar objetos
        generarObjetos();
        //Generar enemigos
        generarEnemigos();
    }

    private void colisiones() {
        colisionPJ = labelPJ.getBounds();
        colisionOro = labelOro.getBounds();
        colisionEspada = labelEspada.getBounds();
        colisionPocion = labelPocion.getBounds();
        colisionMitra = labelMitra.getBounds();

        //PJ vs enemigos
        for (int i = 0; i < enemigosV.size(); i++) {
            colisionEnemigoV = enemigosV.get(i).getBounds();
            if (colisionPJ.intersects(colisionEnemigoV)) {
                colisionEnemigo(i, enemigosV);
            }
        }
        for (int i = 0; i < enemigosH.size(); i++) {
            colisionEnemigoH = enemigosH.get(i).getBounds();
            if (colisionPJ.intersects(colisionEnemigoH)) {
                colisionEnemigo(i, enemigosH);
            }
        }

        if (colisionPJ.intersects(colisionOro)) {
            if (oro < 50){
                oro += 10;
                labelContadorOro.setText("Oro: " + oro);
                labelOro.setLocation((int) (Math.random() * (panelMapa.getWidth() - (2 * labelOro.getWidth()))),
                        (int) (Math.random() * (panelMapa.getHeight() - (2 * labelOro.getHeight()))));
            }
            if (oro == 50) {
                panelMapa.remove(labelOro);
                panelMapa.repaint();
            }
        }else if (colisionPJ.intersects(colisionEspada)){
            panelMapa.remove(labelEspada);
            panelMapa.repaint();
            if (espada == 0){
                espada++;
                labelContadorEspada.setText("Espada: " + espada + "/1");
            }
        }else if (colisionPJ.intersects(colisionMitra)){
            panelMapa.remove(labelMitra);
            panelMapa.repaint();
            if (mitra == 0){
                mitra++;
                labelContadorMitra.setText("Mitra: " + mitra + "/1");
            }
        }else if (colisionPJ.intersects(colisionPocion)){
            panelMapa.remove(labelPocion);
            panelMapa.repaint();
            if (pocion == 0){
                pocion++;
                labelContadorPocion.setText("Pocion: " + pocion + "/1");
            }
        }
    }

    private void colisionEnemigo(int i, ArrayList<JLabel> enemigos) {
        if (labelTipoPJ.getText().equals("Guerrero")) {
            //Guerrero vs enemigo
            if (espada == 0 && vidas > 0) {
                vidas--;
                labelVidas.setText("Vidas: " + vidas);
                labelPJ.setLocation(20, labelPJ.getHeight()/2);
            }
            if (espada > 0 ){
                espada--;
                labelContadorEspada.setText("Espada: " + espada + "/1");
                panelMapa.remove(enemigos.get(i));
                enemigos.remove(i);
                panelMapa.repaint();
            }
        }else if (labelTipoPJ.getText().equals("Mago")){
            //Mago vs enemigo
            if (pocion == 0 && vidas > 0) {
                vidas--;
                labelVidas.setText("Vidas: " + vidas);
                labelPJ.setLocation(20, labelPJ.getHeight()/2);
            }
            if (pocion > 0 ){
                pocion--;
                labelContadorPocion.setText("Pocion: " + pocion + "/1");
                vidas++;
                labelVidas.setText("Vidas: " + vidas);
                labelPJ.setLocation(20, labelPJ.getHeight()/2);
            }
        } else if (labelTipoPJ.getText().equals("Sacerdote")) {
            //Sacerdote vs enemigo
            if (mitra == 0 && vidas > 0) {
                vidas--;
                labelVidas.setText("Vidas: " + vidas);
                labelPJ.setLocation(20, labelPJ.getHeight()/2);
            }
            if (mitra > 0 ){
                mitra--;
                labelContadorMitra.setText("Mitra: " + mitra + "/1");
                labelPJ.setLocation(20, labelPJ.getHeight()/2);
            }
        }
    }

    private void generarEnemigos() {
        generarEnemigosV();
        generarEnemigosH();

        //Timer para movimiento enemigos
        Timer movEnemigo = new Timer(50, new movimientoEnemigos());
        movEnemigo.start();
    }

    private class movimientoEnemigos implements ActionListener {
        boolean movIzquierda = true, movArriba = true;
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < enemigosH.size(); i++) {
                if (movIzquierda){
                    if (enemigosH.get(i).getX() >= 730){
                        actualizarImgEnemigo("src/images/skeleton/skeleton_left.gif", enemigosH, i);
                    }
                    if (enemigosH.get(i).getX() > 29 && enemigosH.get(i).getX() - enemigosH.get(i).getWidth() < panelMapa.getWidth() - labelPJ.getWidth()){
                        enemigosH.get(i).setLocation(enemigosH.get(i).getX() - 5, enemigosH.get(i).getY());
                        if (enemigosH.get(enemigosH.toArray().length-1).getX() <= 40){
                            movIzquierda = false;
                        }
                    }
                }else {
                    if (enemigosH.get(i).getX() == 36){
                        actualizarImgEnemigo("src/images/skeleton/skeleton_right.gif", enemigosH, i);
                    }
                    if (enemigosH.get(i).getX() > 20){
                        enemigosH.get(i).setLocation(enemigosH.get(i).getX() + 5, enemigosH.get(i).getY());
                        if (enemigosH.get(enemigosH.toArray().length-1).getX() >= 730){
                            movIzquierda = true;
                        }
                    }
                }
            }
            for (int i = 0; i < enemigosV.size(); i++) {
                if (movArriba){
                    if (enemigosV.get(i).getY() >= 544){
                        actualizarImgEnemigo("src/images/skeleton/skeleton_up.gif", enemigosV, i);
                    }
                    if (enemigosV.get(i).getY() > 20 && enemigosV.get(i).getY() - enemigosV.get(i).getHeight() < panelMapa.getHeight() - labelPJ.getHeight()){
                        enemigosV.get(i).setLocation(enemigosV.get(i).getX(), enemigosV.get(i).getY() - 5);
                        if (enemigosV.get(enemigosV.toArray().length-1).getY() <= 24){
                            movArriba = false;
                        }
                    }
                }else {
                    if (enemigosV.get(i).getY() == 24){
                        actualizarImgEnemigo("src/images/skeleton/skeleton_down.gif", enemigosV, i);
                    }
                    if (enemigosV.get(i).getY() > 20){
                        enemigosV.get(i).setLocation(enemigosV.get(i).getX(), enemigosV.get(i).getY() + 5);
                        if (enemigosV.get(enemigosV.toArray().length-1).getY() >= 544){
                            movArriba = true;
                        }
                    }
                }
            }
        }
        private void actualizarImgEnemigo(String ruta, ArrayList<JLabel> enemigo, int i) {
            ImageIcon imagen = new ImageIcon(ruta);
            Icon icono = new ImageIcon(
                imagen.getImage().getScaledInstance(enemigo.get(i).getWidth(), enemigo.get(i).getHeight(), Image.SCALE_DEFAULT)
            );
            enemigo.get(i).setIcon(icono);
        }
    }

    private void generarEnemigosH() {
        for (int i = 0; i < 3; i++) {
            labelEnemigoH = new JLabel();
            labelEnemigoH.setSize(32, 32);
            ImageIcon enemigoH = new ImageIcon("src/images/skeleton/skeleton_left.gif");
            Icon iconoEnemigoH = new ImageIcon(
                    enemigoH.getImage().getScaledInstance(labelEnemigoH.getWidth(), labelEnemigoH.getHeight(), Image.SCALE_DEFAULT)
            );
            labelEnemigoH.setIcon(iconoEnemigoH);
            labelEnemigoH.setLocation((panelMapa.getWidth() - (2 * labelEnemigoH.getWidth())),
                    (int) (Math.random() * (panelMapa.getHeight() - (4 * labelEnemigoH.getHeight()))));

            panelMapa.add(labelEnemigoH);
            panelMapa.setComponentZOrder(labelEnemigoH, 0);

            enemigosH.add(labelEnemigoH);
        }
    }

    private void generarEnemigosV() {
        for (int i = 0; i < 3; i++) {
            labelEnemigoV = new JLabel();
            labelEnemigoV.setSize(32, 32);
            ImageIcon enemigoV = new ImageIcon("src/images/skeleton/skeleton_up.gif");
            Icon iconoEnemigoV = new ImageIcon(
                    enemigoV.getImage().getScaledInstance(labelEnemigoV.getWidth(), labelEnemigoV.getHeight(), Image.SCALE_DEFAULT)
            );
            labelEnemigoV.setIcon(iconoEnemigoV);
            labelEnemigoV.setLocation((int) (Math.random() * (panelMapa.getWidth() - (4 * labelEnemigoV.getWidth()))),
                    panelMapa.getHeight() - (2 * labelEnemigoV.getHeight()));

            panelMapa.add(labelEnemigoV);
            panelMapa.setComponentZOrder(labelEnemigoV, 0);

            enemigosV.add(labelEnemigoV);
        }
    }

    private void generarObjetos() {
        //Generar espada
        labelEspada = new JLabel();
        labelEspada.setSize(32, 32);
        ImageIcon espada = new ImageIcon("src/images/dungeon/sword.png");
        Icon iconoEspada = new ImageIcon(
                espada.getImage().getScaledInstance(labelEspada.getWidth(), labelEspada.getHeight(), Image.SCALE_DEFAULT)
        );
        labelEspada.setIcon(iconoEspada);
        labelEspada.setLocation((int) (Math.random() * (panelMapa.getWidth() - (2 * labelEspada.getWidth()))),
                (int) (Math.random() * (panelMapa.getHeight() - (2 * labelEspada.getHeight()))));

        panelMapa.add(labelEspada);
        panelMapa.setComponentZOrder(labelEspada, 0);

        //Generar pocion
        labelPocion = new JLabel();
        labelPocion.setSize(32, 32);
        ImageIcon pocion = new ImageIcon("src/images/dungeon/potion.png");
        Icon iconoPocion = new ImageIcon(
                pocion.getImage().getScaledInstance(labelPocion.getWidth(), labelPocion.getHeight(), Image.SCALE_DEFAULT)
        );
        labelPocion.setIcon(iconoPocion);
        labelPocion.setLocation((int) (Math.random() * (panelMapa.getWidth() - (2 * labelPocion.getWidth()))),
                (int) (Math.random() * (panelMapa.getHeight() - (2 * labelPocion.getHeight()))));

        panelMapa.add(labelPocion);
        panelMapa.setComponentZOrder(labelPocion, 0);

        //Generar mitra
        labelMitra = new JLabel();
        labelMitra.setSize(32, 32);
        ImageIcon mitra = new ImageIcon("src/images/dungeon/mitra.png");
        Icon iconoMitra = new ImageIcon(
                mitra.getImage().getScaledInstance(labelMitra.getWidth(), labelMitra.getHeight(), Image.SCALE_DEFAULT)
        );
        labelMitra.setIcon(iconoMitra);
        labelMitra.setLocation((int) (Math.random() * (panelMapa.getWidth() - (2 * labelMitra.getWidth()))),
                (int) (Math.random() * (panelMapa.getHeight() - (2 * labelMitra.getHeight()))));

        panelMapa.add(labelMitra);
        panelMapa.setComponentZOrder(labelMitra, 0);

        //Generar oro
        labelOro = new JLabel();
        labelOro.setSize(32, 32);
        ImageIcon oro = new ImageIcon("src/images/dungeon/dollar.png");
        Icon iconoOro = new ImageIcon(
                oro.getImage().getScaledInstance(labelOro.getWidth(), labelOro.getHeight(), Image.SCALE_DEFAULT)
        );
        labelOro.setIcon(iconoOro);
        labelOro.setLocation((int) (Math.random() * (panelMapa.getWidth() - (2 * labelOro.getWidth()))),
                (int) (Math.random() * (panelMapa.getHeight() - (2 * labelOro.getHeight()))));

        panelMapa.revalidate();
        panelMapa.repaint();

        panelMapa.add(labelOro);
        panelMapa.setComponentZOrder(labelOro, 0);

        Timer colisionesTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colisiones();
            }
        });
        colisionesTimer.start();
    }
    private void dibujarSuelo(int fila, JLabel labelMuro) {
        int x = 0, y=fila*labelMuro.getHeight();
        while (x < panelMapa.getWidth()) {
            JLabel labelSuelo = new JLabel();
            labelSuelo.setSize(32, 32);
            ImageIcon suelo = new ImageIcon("src/images/dungeon/suelo.png");
            Icon iconoSuelo = new ImageIcon(
                    suelo.getImage().getScaledInstance(labelSuelo.getWidth(), labelSuelo.getHeight(), Image.SCALE_DEFAULT)
            );
            labelSuelo.setIcon(iconoSuelo);
            labelSuelo.setLocation(x, y);

            panelMapa.add(labelSuelo);

            x += labelSuelo.getWidth();
        }
    }
    private void mostrarPanelSeleccion() {
        panelSeleccion = new JPanel();
        panelSeleccion.setLocation(0,0);
        panelSeleccion.setSize(panelMain.getWidth(), 32);
        panelSeleccion.setBackground(Color.lightGray);

        panelMain.add(panelSeleccion);

        //Seleccion de personajes
        JLabel seleccionaPersonaje = new JLabel();
        seleccionaPersonaje.setText("Selecciona un personaje:");
        seleccionaPersonaje.setSize(100, 50);

        panelSeleccion.add(seleccionaPersonaje);

        //Botones para personajes
        botonMago = new JButton();
        botonMago.setText("Mago");
        botonMago.setFocusPainted(false);
        botonMago.setSize(100, 50);
        botonMago.setBackground(new Color(70, 180, 220));
        botonMago.setForeground(Color.white);

        botonGuerrero = new JButton();
        botonGuerrero.setText("Guerrero");
        botonGuerrero.setFocusPainted(false);
        botonGuerrero.setSize(100, 50);
        botonGuerrero.setBackground(new Color(110, 145, 155));
        botonGuerrero.setForeground(Color.white);

        botonSacerdote = new JButton();
        botonSacerdote.setText("Sacerdote");
        botonSacerdote.setFocusPainted(false);
        botonSacerdote.setSize(100, 50);
        botonSacerdote.setBackground(new Color(25, 125, 160));
        botonSacerdote.setForeground(Color.white);

        panelSeleccion.add(botonMago);
        panelSeleccion.add(botonGuerrero);
        panelSeleccion.add(botonSacerdote);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Image icono = pantalla.getImage("src/images/politecnics.png");
        frame.setIconImage(icono);

        JLabel labelMuro = new JLabel(new ImageIcon("src/images/dungeon/muro.png"));
        JLabel labelSuelo = new JLabel(new ImageIcon("src/images/dungeon/suelo.png"));
    }
}