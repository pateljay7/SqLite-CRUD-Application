package com.example.mad_lab_9;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;

class EmployeeListAdapter extends ArrayAdapter<Employee> {
    private static final String TAG = "PersonListAdapter";
    private Context mContext;
    private int mResource;
    private static class ViewHolder {
        TextView name;
        TextView pass;
        TextView email;

    }
    public EmployeeListAdapter(Context context, int resource, ArrayList<Employee> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
       public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String email = getItem(position).getEmail();
        String pass = getItem(position).getPassword();
        Employee emp = new Employee(name,email,pass);
        final View result;
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textViewName);
            holder.email = (TextView) convertView.findViewById(R.id.textemail);
            holder.pass = (TextView) convertView.findViewById(R.id.textpassword);
            result = convertView;
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        holder.name.setText(emp.getName());
        holder.email.setText(emp.getEmail());
        holder.pass.setText(emp.getPassword());
        return convertView;
    }
}

























