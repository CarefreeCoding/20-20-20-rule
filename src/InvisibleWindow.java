import javax.swing.*;
import java.awt.*;

public class InvisibleWindow extends JFrame
{
	private JLabel label;

	public InvisibleWindow()
	{
		this("");
	}

	public InvisibleWindow(String text)
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
											   .getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		Dimension size = new Dimension(width, height);

		label = new JLabel(text);
		label.setFont(label.getFont().deriveFont(Font.BOLD, width / 8));

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setSize(size);
		panel.setMaximumSize(size);
		panel.setMinimumSize(size);
		panel.setPreferredSize(size);
		panel.setOpaque(false);

		panel.add(label);

		setSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		add(panel);
		pack();
		setLocation(0, 0);
	}

	public JLabel getLabel()
	{
		return label;
	}

	public void setText(String text)
	{
		getLabel().setText(text);
	}

	public void popup()
	{
		setAlwaysOnTop(true);
		setVisible(true);
		requestFocus();
	}

	public void remove()
	{
		setAlwaysOnTop(false);
		setVisible(false);
	}
}
