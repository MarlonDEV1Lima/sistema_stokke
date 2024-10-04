package view;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundBorder extends AbstractBorder {
    private int radius;
    private java.awt.Color color;

    public RoundBorder(int radius, java.awt.Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.draw(new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius));
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(java.awt.Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    public static JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath));
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(61, 61, 61));
        button.setForeground(Color.WHITE);
        button.setBorder(new RoundBorder(20, new Color(110, 110, 110)));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 60));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(110, 110, 110));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(61, 61, 61));
            }
        });

        return button;
    }

}
