import javax.swing.*;
import java.awt.*;

public class InvisibleWindow extends JFrame
{
	private JLabel label;

	public InvisibleWindow()
	{
		this(GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice());
	}

	public InvisibleWindow(GraphicsDevice device)
	{
		int width = device.getDisplayMode().getWidth();
		int height = device.getDisplayMode().getHeight();
		Dimension size = new Dimension(width, height);

		label = new JLabel();
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
		setFocusable(false);
		setAutoRequestFocus(false);
		setFocusableWindowState(false);
	}

	public JLabel getLabel()
	{
		return label;
	}

	public void setText(String text)
	{
		getLabel().setText("<html><p>" + text + "</p></html>");
	}

	public void popup()
	{
		pack();
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
