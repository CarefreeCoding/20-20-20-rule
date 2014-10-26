import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class TimerControl
{
	public static final int SECOND = 1000;
	public static final int MINUTE = SECOND + 60;

	private InvisibleWindow message;

	private LinkedList<Message> queue;

	private Timer counter;
	private int   time;

	public TimerControl()
	{
		message = new InvisibleWindow();
		ActionListener event = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				eventAction();
			}
		};
		counter = new Timer(0, event);
	}

	private void eventAction()
	{
		stop();
		if (shouldWeDisplay())
		{
			displayMessage();
		}
		else
		{
			waitForNextMessage();
			if (time > queue.peek().getTime())
			{
				time = 0;
			}
			else
			{
				time += counter.getDelay() / MINUTE;
			}
		}
	}

	public void start(List<MessageInput> messageInputs)
	{
		queue = Utils.organizeMessages(messageInputs);
		time = 0;
		eventAction();
	}

	public void stop()
	{
		counter.stop();
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				message.remove();
			}
		});
	}

	private void displayMessage()
	{
		// get the message we will display
		Message msg = queue.poll();
		int timeToSleep = msg.getDuration();
		// prepare
		counter.setDelay(timeToSleep * SECOND);
		// set text and display it
		message.setText(msg.getText());
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				message.popup();
			}
		});
		// add it to the end of the queue
		queue.add(msg);
		// start waiting
		counter.start();
	}

	private void waitForNextMessage()
	{
		// see how long we have to wait
		int timeToSleep = queue.peek().getTime() - time;
		// prepare
		counter.setDelay(timeToSleep * MINUTE);
		// start waiting
		counter.start();
	}

	private boolean shouldWeDisplay()
	{
		int t = queue.peek().getTime();
		return time == t;
	}

	private void close()
	{
		counter.stop();
		message.dispose();
	}
}
