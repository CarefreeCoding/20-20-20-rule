import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InvisibleWindows
{
	private List<InvisibleWindow> windows;

	public InvisibleWindows()
	{
		GraphicsDevice[] screens = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getScreenDevices();

		windows = new ArrayList<InvisibleWindow>();
		for (GraphicsDevice screen : screens)
		{
			windows.add(new InvisibleWindow(screen));
		}
	}

	public void setText(String text)
	{
		for (InvisibleWindow window : windows)
		{
			window.setText(text);
		}
	}

	public void show()
	{
		for (InvisibleWindow window : windows)
		{
			window.popup();
		}
	}

	public void hide()
	{
		for (InvisibleWindow window : windows)
		{
			window.remove();
		}
	}

	public void dispose()
	{
		for (InvisibleWindow window : windows)
		{
			window.dispose();
		}
	}
}
