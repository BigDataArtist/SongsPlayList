package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.List;

public class KaraokeMachine {

	private SongBook mSongBook;
	
	private BufferedReader mReader;
	
	private Queue<Song> mSongQueue;
	private Map<String, String> mMenu;
	// Lets give it songs
	
	public  KaraokeMachine(SongBook songBook){
		
		mSongBook = songBook;
		
		mReader = new BufferedReader(new InputStreamReader(System.in));
		mSongQueue = new ArrayDeque<Song>();
		mMenu = new HashMap<String,String>();
		
		mMenu.put("add", " add  a new song to the book");
		mMenu.put("choose", "choose a song to sing");
		mMenu.put("Play"," Play next song");
		mMenu.put("quit", " leave it :Exit the program ");	
	}
	
	private String promptAction() throws IOException{
		
		System.out.printf(" There are %d songs available and %d in the queue, your options are %n",mSongBook.getSongCount(),mSongQueue.size());
		
		
		for(Map.Entry<String,String> option: mMenu.entrySet()){
			System.out.printf("%s - %s %n" , option.getKey(), option.getValue());
		}
		
		System.out.print(" What do you want to do");
		
		String choice = mReader.readLine();
		
		return choice.trim().toLowerCase();	
	}
	
	public void run(){
		
		String choice = "";
		
		do{
		try{
			choice = promptAction();
			
			switch(choice){
			case "add":
				Song song = promptNewSong();
				mSongBook.addSong(song);
				System.out.printf("%s added ! %n%n", song); // This will invoke the To String method in the Song class
				break;
				
			case "choose":
				String artist = PromptArtist();
				Song artistSong = promptSongForArtist(artist);
				mSongQueue.add(artistSong);
				System.out.printf(" you cose %s%n", artistSong);
			
			case "quit":
				System.out.println(" Thanks for the choosing the songs");
				break;
			case "play":
				playNext();
				break;
				default:
					System.out.printf("Unknown choice : '%s' . Try again %n%n%n", choice);
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
		
		while(!choice.equals("quit"));
}
	
	//Code to Add The new song.
	
	private Song promptNewSong() throws IOException{  // Return tyoe is Song bcz its returning to the class Song
		System.out.print(" Enter the Artist Name");
		String artist = mReader.readLine();
		System.out.print(" Enter the Title");
		String title = mReader.readLine();
		System.out.print(" Enter the Video URL");
		String URL = mReader.readLine();
		return new Song(artist,title,URL);
	}
	
	private String PromptArtist() throws IOException{
		System.out.println("Available artist");
		List<String> artists = new ArrayList<String>(mSongBook.getArtists());
		int index = PromptForIndex(artists);
		return artists.get(index);
	}
	
	private Song promptSongForArtist(String artist) throws IOException{
		List<Song> songs = mSongBook.getSongForArtist(artist);
		List<String> songTitles = new ArrayList<>();
		for( Song song : songs){
			songTitles.add(song.getTitile());
		}
		System.out.printf(" Available songs for %s, %n",artist);
		int index = PromptForIndex(songTitles);
		return songs.get(index);
	}
	
	
	
	private int PromptForIndex(List<String> artistsName) throws IOException{
		int count = 1;
		
		for(String option : artistsName){
			System.out.printf(" %d.) %s %n", count,option);
			count++;
		}
		System.out.print(" Your choice");
		String OptionAsString = mReader.readLine();
		int choice = Integer.parseInt(OptionAsString.trim());
		return choice -1;		
	}
	
	
	public void playNext(){
		Song song = mSongQueue.poll(); //Takes the next song available if there is one or null if no song there
		if( song == null){
			System.out.println(" Sorry no songs in the queue"+ " use choose in switch statement to add some.");
		}
		else{
			System.out.printf("%n%n%n open %s to hear %s by %s", song.getVideoUrl(),song.getArtist(),song.getTitile());
		}
		
	}
	
	
	
}