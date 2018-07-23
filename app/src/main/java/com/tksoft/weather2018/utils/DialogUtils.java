package com.tksoft.weather2018.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;



public class DialogUtils {
    private static final int REQUEST_CODE_SETTINGS = 101;
    private static final String SHOWCASE_ID = "sequence example";

    public static void showSettingsDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
                openSettings(context);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public static void openSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }



    public static void showCaseViewScanFragment(Activity activity, View btnSwitchCamera,
                                                View btnOnOffFlashFlight, View btnScanGallery ) {
//        ShowcaseConfig config = new ShowcaseConfig();
//        config.setDelay(500);
//        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(activity, SHOWCASE_ID);
//        sequence.setConfig(config);
//        sequence.addSequenceItem(
//                new MaterialShowcaseView.Builder(activity)
//                        .setTarget(btnSwitchCamera)
//                        .setDismissText(activity.getString(R.string.lbl_string_go_it))
//                        .setContentText("This is action switch camera")
//                        .withRectangleShape()
//                        .build()
//        );
//
//        sequence.addSequenceItem(
//                new MaterialShowcaseView.Builder(activity)
//                        .setTarget(btnOnOffFlashFlight)
//                        .setDismissText(activity.getString(R.string.lbl_string_go_it))
//                        .setContentText("This is action on/off flash")
//                        .withRectangleShape()
//                        .build()
//        );
//
//        sequence.addSequenceItem(
//                new MaterialShowcaseView.Builder(activity)
//                        .setTarget(btnScanGallery)
//                        .setDismissText(activity.getString(R.string.lbl_string_go_it))
//                        .setContentText("This is action scanner qrcode from gallery")
//                        .withRectangleShape()
//                        .build()
//        );
//        sequence.start();
    }


}
