package com.example.addy.notificationsservice;

/*для отправки уведомлений необходим NotificationManager
* подробнее - см по тексту*/

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Addy on 09.01.2018.
 */

public class MyService extends Service {
    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//используется для отправки уведомлений
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification() {
        Notification.Builder nb = new Notification.Builder(this);
        //1-я часть
        nb.setSmallIcon(R.drawable.smile);
        nb.setTicker("text in status bar");
        nb.setWhen(System.currentTimeMillis());
        //передаваемая иконка и текст будут видны в статус баре
        //setWhen - текущее время(обычно). по нему уведомления будут отсортированы


        //3-я часть
        Intent intent = new Intent(this, MainActivity.class);//создаем интент, который бы мы использовали для вызова нашего активити
        intent.putExtra(MainActivity.FILE_NAME, "somefile");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //оборачиваем интент в пендингИнтент, и теперь он знает, что надо вызывать активити и содержит интент, к-ый для этой цели надо использовать
        //заданное активити будет вызываться при нажатии на уведомление

        //2-я часть
        nb.setContentTitle("notification`s title");
        nb.setContentText("notification`s text");
        nb.setContentIntent(pendingIntent);
        //ставим флаг, чтобы уведомление пропало после нажатия
        //по умолчанию, оно продолжает висеть
        nb.setAutoCancel(true);
        nb.build();
        //отправляем
        Notification notification = nb.getNotification();
        notificationManager.notify(1, notification);
        //id используется если мы хотим изменить или удалить уведомление
        //например если отправить уведомления с одинаковыми id то новое уведомление заменит старое
        //чтобы просто отправить новое уведомление вдобавок к ранее отправленному, надо заменить id
        //также по id  можно удалить требуемое уведомление с помощью метода cancel()
        //или удалить все уведомления методом cancelAll()
        //настроить звуковое/вибро- оповещение об уведомлениях можно добавлением констант DEFAULT_SOUND и DEFAULT_VIBRATE
        //свой звук можно установить с помощью notification.sound = Uri.parse("<путь к mp3 файлу>")
        //можно также настроить уведомление о нотификации через световой индикатор - цвет, частота и тд
        //можно настроить напоминалки, если нотификация не была прочитана
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
