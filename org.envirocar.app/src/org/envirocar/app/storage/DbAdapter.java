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

package org.envirocar.app.storage;

import java.util.ArrayList;
import java.util.List;

import org.envirocar.app.exception.MeasurementsException;
import org.envirocar.app.storage.Track.TrackStatus;


/**
 * DB Adapter Interface that saves measurements in a local SQLite Database
 * 
 * @author jakob
 * 
 */

public interface DbAdapter {

	/**
	 * Method to open the DB connection
	 * 
	 * @return DbAdapter Object that can be used to call the other methods
	 * @deprecated implementations should take care of that on their own
	 */
	@Deprecated
	public DbAdapter open();

	/**
	 * Close the DB connection. Should be called when the app stops
	 */
	public void close();

	/**
	 * Check whether the database is opened at the moment.
	 * @return true if db is open.
	 */
	public boolean isOpen();

	/**
	 * Inserts a measurements into the database
	 * 
	 * @param measurement
	 *            The measurement that should be inserted
	 * @throws MeasurementsException 
	 * @throws TrackAlreadyFinishedException 
	 */
	public void insertNewMeasurement(Measurement measurement) throws MeasurementsException, TrackAlreadyFinishedException;

	/**
	 * Inserts a track into the database
	 * 
	 * @param track
	 *            The track that should be inserted
	 * @return the id of the track that has been inserted
	 */
	public long insertTrack(Track track);

	/**
	 * Updates a Track in the database
	 * 
	 * @param track
	 *            the track to update
	 * @return true or false if the query was successful
	 */
	public boolean updateTrack(Track track);

	/**
	 * Returns all tracks as an ArrayList<Track>
	 * 
	 * @return All tracks in an ArrayList
	 */
	public ArrayList<Track> getAllTracks();

	/**
	 * @param lazyMeasurements if true, an implementation shall return
	 * {@link Track} objects that load their measurements in lazy fashion
	 * @return all tracks
	 * @throws TrackWithoutMeasurementsException 
	 */
	public List<Track> getAllTracks(boolean lazyMeasurements);
	
	/**
	 * Returns one track specified by the id
	 * 
	 * @param id
	 *            The id of the track that should be returned
	 * @return The desired track or null if it does not exist
	 * @throws TrackWithoutMeasurementsException 
	 */
	public Track getTrack(long id) throws TrackWithoutMeasurementsException;
	
	/**
	 * Returns one track specified by the id
	 * 
	 * @param id the tracks internal id
	 * @param lazyMeasurements if true, an implementation shall return a
	 * {@link Track} that loads its measurements in lazy fashion
	 * @return the desired track
	 * @throws TrackWithoutMeasurementsException 
	 */
	public Track getTrack(long id, boolean lazyMeasurements) throws TrackWithoutMeasurementsException;
	
	/**
	 * Returns <code>true</code> if a track with the given id is in the Database
	 * 
	 * @param id
	 * 		The id id ot the checked track
	 * @return exists a track with the id
	 */
	public boolean hasTrack(long id);

	/**
	 * Deletes all tracks and measurements in the database
	 */
	public void deleteAllTracks();

	/**
	 * Returns the number of stored tracks in the SQLite database
	 */
	public int getNumberOfStoredTracks();

	/**
	 * Retruns the track that was last inserted into the database
	 * 
	 * @return the latest track of the DB or null if there are no tracks
	 */
	public Track getLastUsedTrack();
	
	/**
	 * Delete track specified by id.
	 * 
	 * @param id
	 *            id of the track to be deleted.
	 */
	public void deleteTrack(long id);

	public int getNumberOfRemoteTracks();

	public int getNumberOfLocalTracks();

	/**
	 * an implementation shall delete (!) all
	 * local tracks from the underlying persistence
	 * layer.
	 * 
	 * Friendly warning: every local track (= not yet
	 * uploaded) will be lost irreversible. 
	 */
	public void deleteAllLocalTracks();

	/**
	 * an implementation shall remove
	 * all local representations of remote tracks from
	 * the underlying persistence layer.
	 */
	public void deleteAllRemoteTracks();

	public List<Track> getAllLocalTracks();

	/**
	 * an implementation shall create a new track. if there is a previously
	 * used track ({@link #getLastUsedTrack()} != null), that track shall
	 * be finialized ({@link Track#setStatus(org.envirocar.app.storage.Track.TrackStatus)} 
	 * with status {@link TrackStatus#FINISHED} - e.g. through the {@link #finishCurrentTrack()}
	 * implementation).
	 * 
	 * @return the new track
	 */
	public Track createNewTrack();

	/**
	 * an implemetation shall finialize the currrent
	 * track ({@link #getLastUsedTrack()}). set the {@link Track#setStatus(org.envirocar.app.storage.Track.TrackStatus)} 
	 * with status {@link TrackStatus#FINISHED}.
	 * @return the finished track or null if there was no track
	 */
	public Track finishCurrentTrack();

	/**
	 * an implementation shall return all meaasurements
	 * for the given track.
	 * 
	 * @param track the track object
	 * @return the list of Measurements
	 * @throws TrackWithoutMeasurementsException
	 */
	List<Measurement> getAllMeasurementsForTrack(Track track) throws TrackWithoutMeasurementsException;

	/**
	 * an implementation shall update the ID
	 * of all Track's cars which currently have the currentId
	 * and update it to newId.
	 * 
	 * @param currentId
	 * @param newId
	 */
	public void updateCarIdOfTracks(String currentId, String newId);

	void insertMeasurement(Measurement measurement) throws MeasurementsException;

	
}
