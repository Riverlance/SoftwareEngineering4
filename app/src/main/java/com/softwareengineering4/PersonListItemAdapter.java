package com.softwareengineering4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

public class PersonListItemAdapter extends BaseAdapter {
    ArrayList<PersonListItem> personListItems;
    Context context;

    public PersonListItemAdapter(Context context) {
        personListItems = new ArrayList<>();
        this.context = context;

        // Valor inicial padrão
        // personListItems.add(new PersonListItem("Title", "Subtitle"));
    }

    @Override
    public int getCount() {
        return personListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return personListItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.list_item_person, viewGroup, false);

        TextView titleTextView = row.findViewById(R.id.titleTextView);
        TextView subtitleTextView = row.findViewById(R.id.subtitleTextView);
        ImageView profileImageView = row.findViewById(R.id.profileImageView);

        PersonListItem personListItem = personListItems.get(i);
        titleTextView.setText(WordUtils.capitalize(personListItem.getName(true)));
        subtitleTextView.setText(personListItem.username);
        if (!personListItem.pictureThumbnailURL.equals("")) {
            Picasso.with(context).load(personListItem.pictureThumbnailURL).into(profileImageView); // "https://randomuser.me/api/portraits/thumb/men/53.jpg"
        }

        return row;
    }
}
