import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerControl
{
	private InvisibleWindow message;

	private LinkedList<Message> queue;

	private ScheduledExecutorService timer;
	private Runnable                 task;

	private int time;

	public TimerControl()
	{
		message = new InvisibleWindow();

		task = new Runnable()
		{
			@Override
			public void run()
			{
				eventAction();
			}
		};
		timer = Executors.newSingleThreadScheduledExecutor();
	}

	private void eventAction()
	{
		stop(true);
		if (shouldWeDisplay())
		{
			displayMessage();
		}
		else
		{
			waitForNextMessage();
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
		stop(false);
	}

	private void stop(boolean soft)
	{
		if (!soft)
		{
			timer.shutdownNow();
			timer = Executors.newSingleThreadScheduledExecutor();
		}
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
		// set text
		message.setText(msg.getText());
		// find out duration of wait
		int timeToSleep = msg.getDuration();
		// add it to the end of the queue
		queue.add(msg);
		// display it
		message.popup();
		// schedule and start
		timer.schedule(task, timeToSleep, TimeUnit.SECONDS);
		System.out.println("Display message |" + msg.getText() + "| for " +
								   "|" + timeToSleep + "| seconds");
	}

	private void waitForNextMessage()
	{
		// see how long we have to wait
		int timeToSleep = queue.peek().getTime() - time;
		// schedule and start
		timer.schedule(task, timeToSleep, TimeUnit.MINUTES);

		// see if we have to reset the time because we have breached the end
		// of the loop
		if (time > queue.peek().getTime())
		{
			time = 0;
		}
		else
		{
			time += timeToSleep;
		}
		System.out.println("Wait for |" + timeToSleep + "| minutes");
	}

	private boolean shouldWeDisplay()
	{
		int t = queue.peek().getTime();
		return time == t;
	}

	public void close()
	{
		timer.shutdown();
		message.dispose();
	}
}
