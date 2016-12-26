package com.example.vadim.hw_8;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class Second extends RecyclerView.Adapter<Second.ViewHolder> {

    private String name;
    private String info;
    private String temp;
    private String humidity;
    private String windSpeed;


    Second(String name, String main, String temperature, String humidity, String windSpeed) {
        this.name = name;
        info = main;
        temp = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_second, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText("Місто: " + name +";");
        holder.info.setText("Погодні умови: " + info +";");
        holder.temperature.setText("Температура: " + temp +";");
        holder.humidity.setText("Вологість: " + humidity + "%" +";");
        holder.wind_speed.setText("Швидкість вітру: " + windSpeed + "м/с"+".");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView info;
        private TextView temperature;
        private TextView humidity;
        private TextView wind_speed;

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.ITEM_NAME);
            info = (TextView) view.findViewById(R.id.ITEM_INFO);
            temperature = (TextView) view.findViewById(R.id.ITEM_TEMP);
            humidity = (TextView) view.findViewById(R.id.ITEM_HUMIDITY);
            wind_speed = (TextView) view.findViewById(R.id.ITEM_SPEED);
        }
    }
}
