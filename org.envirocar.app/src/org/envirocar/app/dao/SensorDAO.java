/* 
 * enviroCar 2013
 * Copyright (C) 2013  
 * Martin Dueren, Jakob Moellers, Gerald Pape, Christopher Stephan
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 * 
 */
package org.envirocar.app.dao;

import java.util.List;

import org.envirocar.app.model.Car;

public interface SensorDAO {
	
	/**
	 * an implementation shall return the list of sensors its data backend
	 * contains.
	 * 
	 * @return the list of all sensors provided through this DAO
	 * @throws SensorRetrievalException if the data backend cannot be accessed
	 */
	public List<Car> getAllSensors() throws SensorRetrievalException;

	/**
	 * an implementation shall save the given car at the underlying
	 * data backend or throw a {@link NotConnectedException} if it cannot
	 * store the car.
	 * 
	 * @param car the sensor to save
	 * @param user the user for authentication reasons
	 * @return the ID of the saved sensor as provided by the underlying DAO
	 * @throws NotConnectedException
	 */
	public String saveSensor(Car car) throws NotConnectedException;
	
}
