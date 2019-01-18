package com.dhwani.carpark;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CarParkManager {

	public static void main(String[] args) {
		String[] carsActions = args[0].split(",");
		int ticketNo = 5000;
		Map<String, String> ticketCarMap = new HashMap<String, String>();
		String[] parkingLane = new String[10];
		Arrays.fill(parkingLane,"");
		for (String carAction : carsActions) 
		{
			char action = carAction.charAt(0);
			String carTicket = carAction.substring(1, carAction.length()).trim();
			switch(action)
			{
			case 'p':
				ticketCarMap.put(String.valueOf(ticketNo), carTicket);
				ticketNo++;
				boolean parked = false;
				for(int index=0; index<10; index++)
				{
					if(parkingLane[index].equals(""))
					{
						parkingLane[index] = carTicket;
						parked = true;
						break;
					}
				}
				if(!parked)
				{
					System.out.println("Parking Full. Car "+carTicket+" was unable to park the car");
				}
				break;
			case 'u':
				String unparkCar = ticketCarMap.get(carTicket);
				parkingLane[Arrays.asList(parkingLane).lastIndexOf(unparkCar)]="";
				break;
			case 'c':
				String[] parkingLaneCompact = Arrays.stream(parkingLane)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);
				Arrays.fill(parkingLane, "");
				int plcIndex = 0;
				for (String plc : parkingLaneCompact) {
					parkingLane[plcIndex] = plc;
					plcIndex++;
				}
				break;
			}
		}
		
		String output = String.join(",", parkingLane);
		System.out.println(output.toString());
	}

}
