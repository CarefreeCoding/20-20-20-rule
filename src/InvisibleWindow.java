import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class InvisibleWindow extends JDialog
{
	private JLabel label;

	public InvisibleWindow()
	{
		this(GraphicsEnvironment.getLocalGraphicsEnvironment()
								.getDefaultScreenDevice());
	}

	public InvisibleWindow(GraphicsDevice device)
	{
		Point location =
				device.getDefaultConfiguration().getBounds().getLocation();
		Dimension size =
				device.getDefaultConfiguration().getBounds().getSize();

		label = new JLabel();
		label.setFont(label.getFont().deriveFont(Font.BOLD,
												 (int) size.getWidth() / 8));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		setLayout(new MigLayout("wrap 1"));
		setSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
		setLocation(location);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		add(label, "dock center");
		pack();
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
