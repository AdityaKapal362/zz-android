package tw.music.streamer.notification;

import android.os.Build;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;

import tw.music.streamer.service.ZryteZenePlay;
import tw.music.streamer.StreamerActivity;

public class ZryteZeneNotification {
	
	public static Notification setup(Context a) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel ch = new NotificationChannel(
			ZryteZenePlay.CHANNEL_ID,
			"Music Playback",
			NotificationManager.IMPORTANCE_HIGH
			);
			ch.setSound(null, null);
			ch.enableLights(false);
			ch.enableVibration(false);
			NotificationManager mr = a.getSystemService(NotificationManager.class);
			if (mr != null) {
				mr.createNotificationChannel(ch);
			}
		}

		Intent openAppIntent = new Intent(a, StreamerActivity.class);
    	PendingIntent openAppPendingIntent = PendingIntent.getActivity(a, 0, openAppIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
		
		Notification notification = new Notification.Builder(a, ZryteZenePlay.CHANNEL_ID)
		.setContentTitle("ZryteZene")
		.setContentText("Idle...")
		.setSmallIcon(android.R.drawable.ic_media_play)
		.setContentIntent(openAppPendingIntent)
		.setOnlyAlertOnce(true)
		.build();
		return notification;
	}
	
	public static void update(Context a, String b) {
		Intent openAppIntent = new Intent(a, StreamerActivity.class);
    	PendingIntent openAppPendingIntent = PendingIntent.getActivity(a, 0, openAppIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
		NotificationManager notificationManager = (NotificationManager) a.getSystemService(Context.NOTIFICATION_SERVICE);
		if (notificationManager == null) return;
		Notification notification;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			notification = new Notification.Builder(a, ZryteZenePlay.CHANNEL_ID)
			.setContentTitle("ZryteZene")
			.setContentText(b)
			.setSmallIcon(android.R.drawable.ic_media_play)
			.setContentIntent(openAppPendingIntent)
			.setOngoing(true)
			.setOnlyAlertOnce(true)
			.build();
		} else {
			notification = new Notification.Builder(a)
			.setContentTitle("ZryteZene")
			.setContentText(b)
			.setSmallIcon(android.R.drawable.ic_media_pause)
			.setContentIntent(openAppPendingIntent)
			.setOngoing(true)
			.setOnlyAlertOnce(true)
			.build();
		}
		notificationManager.notify(ZryteZenePlay.NOTIFICATION_ID, notification);
	}
	
}
