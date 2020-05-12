# **Action Mode**

Action mode is how we change tha actionbar or toolbar. In some Android applications, if you select some text, 
the menu on the actionbar/toolbar changes. The copy menu item shows up.

Create a new empty Activity Android application. Name it HelloActionMode1.   
   

Edit app / res / layout / activity_main.xml. Add two buttons. The first button will launch the action mode. The second button will invalidate action mode.  
  
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:tools="http://schemas.android.com/tools"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".MainActivity">
        <Button
                android:text="Launch Action Mode"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/button" app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintHorizontal_bias="0.5" app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintTop_toTopOf="parent" app:layout_constraintBottom_toBottomOf="parent"/>
        <Button
                android:text="Invalidate Action Mode"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/buttonInvalidate" android:layout_marginTop="8dp"
                app:layout_constraintTop_toBottomOf="@+id/button" android:layout_marginBottom="8dp"
                app:layout_constraintBottom_toBottomOf="parent" app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintHorizontal_bias="0.5" app:layout_constraintEnd_toEndOf="parent"/>
    </androidx.constraintlayout.widget.ConstraintLayout>
  
Add three drawable files in app / res / drawable. Name them: ic_call_received_white.xml, ic_fiber_new_white.xml, ic_transfer_within_a_station_white.xml.  
  
Edit app / res / values / strings.xml.  
  
    <resources>
        <string name="app_name">HelloActionMode1</string>
        <string name="new_action">New</string>
        <string name="download_action">Download</string>
        <string name="login_action">Login</string>
    </resources>
  
Create a menu layout resource, app / res / menu / actions.xml. This menu layout will be your menu in Action Mode.  
  
    <?xml version="1.0" encoding="utf-8"?>
    <menu xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">
        <item
                android:id="@+id/newAction"
                android:icon="@drawable/ic_fiber_new_white"
                app:showAsAction="always"
                android:title="@string/new_action"/>
        <item
                android:id="@+id/downloadAction"
                android:icon="@drawable/ic_call_received_white"
                app:showAsAction="ifRoom"
                android:title="@string/download_action"/>
        <item
                android:id="@+id/loginAction"
                android:icon="@drawable/ic_transfer_within_a_station_white"
                app:showAsAction="never"
                android:title="@string/login_action"/>
    </menu>
  
Create a menu layout resource, app / res / menu / actions_invalidate.xml. This menu layout will be your menu in Action Mode after invalidating action mode. Invalidating is similar to refreshing.  