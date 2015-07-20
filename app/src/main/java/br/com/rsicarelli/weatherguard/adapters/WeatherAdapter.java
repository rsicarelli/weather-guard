package br.com.rsicarelli.weatherguard.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

import br.com.rsicarelli.weatherguard.R;
import br.com.rsicarelli.weatherguard.connection.WeatherRequests;
import br.com.rsicarelli.weatherguard.global.WeatherApplication;
import br.com.rsicarelli.weatherguard.responses.ListResult;
import br.com.rsicarelli.weatherguard.utils.StringUtils;

/**
 * Created by rsicarelli on 7/20/15.
 */
public class WeatherAdapter extends BaseAdapter {

    private final DisplayImageOptions options;
    private List<ListResult> list = new ArrayList<>();

    public WeatherAdapter(List<ListResult> list) {
        this.list = list;
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .showImageOnFail(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.NONE)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int location) {
        return list.get(location);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListResult result = (ListResult) getItem(position);

        final ServiceRow holder;
        if (convertView == null || convertView.getTag() == null) {
            holder = new ServiceRow();

            LayoutInflater inflater = LayoutInflater.from(WeatherApplication.getInstance());

            convertView = inflater.inflate(R.layout.item_list, parent, false);

            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_description);
            holder.tvTemp = (TextView) convertView.findViewById(R.id.tv_temp);
            holder.tvHumidity = (TextView) convertView.findViewById(R.id.tv_humidity);
            holder.tvWind = (TextView) convertView.findViewById(R.id.tv_winds);
            holder.imgHolder = (ImageView) convertView.findViewById(R.id.img_view);

            convertView.setTag(holder);
        } else {
            holder = (ServiceRow) convertView.getTag();
        }

        holder.tvName.setText(result.getName());
        holder.tvDesc.setText(StringUtils.captalize(result.getWeather().get(0).getDescription()));
        holder.tvTemp.setText(String.valueOf(StringUtils.kelvinToCelcius(result.getMain().getTemp()) + "°"));
        holder.tvHumidity.setText(String.valueOf(result.getMain().getHumidity()) + "%");
        holder.tvWind.setText(String.valueOf(result.getWind().getSpeed()) + " km/h");

        WeatherRequests.showWeatherIcon(result.getWeather().get(0).getIcon(), holder.imgHolder, options);

        convertView.setTag(holder);

        return convertView;
    }

    public class ServiceRow {
        public TextView tvName;
        public TextView tvDesc;
        public TextView tvTemp;
        public TextView tvHumidity;
        public TextView tvWind;
        public ImageView imgHolder;
    }
}
