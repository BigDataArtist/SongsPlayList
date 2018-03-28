package model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

public class SongBook {
	private List<Song> mSongs;
	
	public SongBook(){
		mSongs = new ArrayList<Song>();
	}
	
public void addSong(Song song){
	
	mSongs.add(song);
	
}

public int getSongCount(){
	return mSongs.size();  // This variable is used in arraylist
}


// This should be cached ??
private Map<String , List<Song>> byArtist(){   // each artist can have multiple songs
	
	Map<String ,List<Song>> byArtist = new HashMap<>();
	
	for(Song song : mSongs){  // This will look in the list of the songs in mSongs
		List<Song> artistSongs = byArtist.get(song.getArtist());
		if(artistSongs == null){
			artistSongs = new ArrayList<Song>();
			byArtist.put(song.getArtist(),artistSongs);
		}
		
		artistSongs.add(song);  // This is Adding the song to the new list or the existing list. artistSongs at line 34 is empty.
	}
	
	return byArtist;
}
	
	public Set<String> getArtists(){
		return byArtist().keySet();    // this will return the key from the hashmap or hashset   // This adds more than one map how??
	}
	
	public List<Song> getSongForArtist(String artistName){
		return byArtist().get(artistName);
	}
}

