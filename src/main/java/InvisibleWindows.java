import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InvisibleWindows
{
	private List<InvisibleWindow> windows;

	private int mainScreenIndex;

	public InvisibleWindows()
	{
		mainScreenIndex = 0;
		GraphicsDevice mainScreen =
				GraphicsEnvironment.getLocalGraphicsEnvironment()
								   .getDefaultScreenDevice();
		GraphicsDevice[] screens = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getScreenDevices();

		windows = new ArrayList<InvisibleWindow>();
		for (int i = 0; i < screens.length; i++)
		{
			windows.add(new InvisibleWindow(screens[i]));
			if (screens[i] == mainScreen)
			{
				mainScreenIndex = i;
			}
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
