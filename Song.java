package model;

public class Song {

	private String mArtist;
	private String mTitle;
	private String mVideoUrl;
	
	public Song(String Artist, String Title, String VideoUrl){
		mArtist = Artist;
		mTitle = Title;
		mVideoUrl = VideoUrl;
	}
	
	public String getTitile(){
		return mTitle;
	}
	
	public String getArtist(){
		return mArtist;
	}
	
	public String getVideoUrl(){
		return mVideoUrl;
	}
	
	@Override
	public String toString(){
		return String.format(" Song: %s by %s ", mTitle,mArtist);
	}
	
}
