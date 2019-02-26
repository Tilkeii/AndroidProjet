package com.example.flo.projetandroid;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */

public class EventAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.event_app_widget);

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

        if(mFirebaseAuth.getCurrentUser() != null){
            mFirestore.collection("events")
                    .whereArrayContains("users", mFirebaseAuth.getUid())
                    .whereGreaterThan("date", new Date())
                    .orderBy("date", Query.Direction.ASCENDING)
                    .limit(1)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot querySnapshot = task.getResult();
                                if (querySnapshot != null) {
                                    Event event = querySnapshot.getDocuments().get(0).toObject(Event.class);
                                    views.setViewVisibility(R.id.errorMessage, View.GONE);
                                    views.setViewVisibility(R.id.linelayoutFirst, View.VISIBLE);
                                    views.setViewVisibility(R.id.linearlayoutSecond, View.VISIBLE);
                                    views.setTextViewText(R.id.titreWidget, event.getTitre());
                                    views.setTextViewText(R.id.lieuWidget, event.getLieu());
                                    views.setTextViewText(R.id.sportWidget, event.getSport());
                                    views.setTextViewText(R.id.dateWidget, event.getDate().toString());
                                } else {
                                    views.setTextViewText(R.id.errorMessage, "No document");
                                }
                            } else {
                                views.setTextViewText(R.id.errorMessage, task.getException().getMessage());
                            }
                            appWidgetManager.updateAppWidget(appWidgetId, views);
                        }
                    });
        } else {
            views.setTextViewText(R.id.errorMessage, "Vous devez etre connecter a l'application");
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

