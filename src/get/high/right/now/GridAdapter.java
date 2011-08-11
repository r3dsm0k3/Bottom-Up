package get.high.right.now;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
	
	private Context context=null;
	
	ViewHolder holder=null;
	
	LayoutInflater mInflater=null; 
	
	private String[] texts = {"FootBall","Cricket", "Carom Board", "Tennis" ,"Hockey" , "Volley Ball", "Ashes"};

	public GridAdapter(Context context) {
	    this.context = context;
	    mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
	    return 7;
	}

	public Object getItem(int position) {
	    return position;
	}

	public long getItemId(int position) {
	    return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	   
	    if (convertView == null) {
	    	holder = new ViewHolder();
	        convertView = mInflater.inflate(R.layout.grid_item, null);
	        holder.tv = (TextView) convertView.findViewById(R.id.grid_item_label);
	        holder.logo_img =(ImageView)convertView.findViewById(R.id.grid_item_icon);
	    }
	  

	    holder.tv.setText(texts[position]);
	    holder.logo_img.setImageResource(R.drawable.icon);
	    return convertView;

	}
	
	static class ViewHolder {
		
		TextView tv;
		ImageView logo_img;
		
	}
}
