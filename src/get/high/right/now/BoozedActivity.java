package get.high.right.now;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;

public class BoozedActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        GridView gridview = (GridView) findViewById(R.id.mygridview);
        GridAdapter grid_obj = new GridAdapter(this);
        gridview.setAdapter(grid_obj);

    }
}