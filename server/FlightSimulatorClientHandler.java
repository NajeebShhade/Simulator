package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.net.UnknownHostException;

import interpreter.FlightSimulatorInterpreter;

public class FlightSimulatorClientHandler implements ClientHandler {

	private int frequency;
	private String clientInput;

	private class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			if (clientInput != null) {
				
				String[] Valueues = clientInput.split(",");

				/*FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/airspeed-indicator/indicated-speed-kt",Double.parseDouble(Valueues[0]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/altimeter/indicated-altitude-ft",Double.parseDouble(Valueues[1]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/altimeter/pressure-alt-ft",Double.parseDouble(Valueues[2]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/indicated-pitch-deg",Double.parseDouble(Valueues[3]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/indicated-roll-deg",Double.parseDouble(Valueues[4]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/internal-pitch-deg",Double.parseDouble(Valueues[5]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/internal-roll-deg",Double.parseDouble(Valueues[6]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/encoder/indicated-altitude-ft",Double.parseDouble(Valueues[7]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/encoder/pressure-alt-ft",Double.parseDouble(Valueues[8]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/gps/indicated-altitude-ft",Double.parseDouble(Valueues[9]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/gps/indicated-ground-speed-kt",Double.parseDouble(Valueues[10]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/gps/indicated-vertical-speed",Double.parseDouble(Valueues[11]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/heading-indicator/indicated-heading-deg", Double.parseDouble(Valueues[12]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/heading-indicator/offset-deg", Double.parseDouble(Valueues[13]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/magnetic-compass/indicated-heading-deg",Double.parseDouble(Valueues[14]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/slip-skid-ball/indicated-slip-skid",Double.parseDouble(Valueues[15]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/turn-indicator/indicated-turn-rate",Double.parseDouble(Valueues[16]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/vertical-speed-indicator/indicated-speed-fpm",Double.parseDouble(Valueues[17]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/aileron", Double.parseDouble(Valueues[18]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/elevator", Double.parseDouble(Valueues[19]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/rudder", Double.parseDouble(Valueues[20]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/flaps", Double.parseDouble(Valueues[21]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/speedbrake",Double.parseDouble(Valueues[22]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/engines/current-engine/throttle", Double.parseDouble(Valueues[23]));
				FlightSimulatorInterpreter.getData().addSimulatorBindValue("/engines/engine/rpm", Double.parseDouble(Valueues[24]));
			}
		}
	}

	public FlightSimulatorClientHandler(int frequency) {
		
		this.frequency = frequency;
		
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/airspeed-indicator/indicated-speed-kt",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/altimeter/indicated-altitude-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/altimeter/pressure-alt-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/indicated-pitch-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/indicated-roll-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/internal-pitch-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/attitude-indicator/internal-roll-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/encoder/indicated-altitude-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/encoder/pressure-alt-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/gps/indicated-altitude-ft",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/gps/indicated-ground-speed-kt",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/gps/indicated-vertical-speed",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/heading-indicator/indicated-heading-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/heading-indicator/offset-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/magnetic-compass/indicated-heading-deg",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/slip-skid-ball/indicated-slip-skid",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/turn-indicator/indicated-turn-rate",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/instrumentation/vertical-speed-indicator/indicated-speed-fpm",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/aileron", 0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/elevator", 0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/rudder",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/flaps",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/flight/speedbrake",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/controls/engines/current-engine/throttle",0.0);
		FlightSimulatorInterpreter.getData().addSimulatorBindValue("/engines/engine/rpm", 0.0);*/
	}

	@Override
	public void handleClient(InputStream input, OutputStream output) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(input));

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new MyTimerTask(), 0, 1000 / frequency);

			while ((clientInput = in.readLine()) != null) {}

			timer.cancel();
			in.close();

		} catch (UnknownHostException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}