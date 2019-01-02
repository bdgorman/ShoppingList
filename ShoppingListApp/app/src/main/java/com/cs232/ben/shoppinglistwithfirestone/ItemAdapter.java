package com.cs232.ben.shoppinglistwithfirestone;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class ItemAdapter extends FirestoreRecyclerAdapter<Item, ItemAdapter.ItemHolder> {
	private OnItemClickListener listener;

	public ItemAdapter(@NonNull FirestoreRecyclerOptions<Item> options) {
		super(options);
	}

	@Override
	protected void onBindViewHolder(@NonNull ItemHolder holder, int position, @NonNull Item model) {
		holder.textViewTitle.setText(model.getName());
		holder.textViewDescription.setText(model.getPrice());
		holder.textViewPriority.setText(String.valueOf(model.getPriority()));
	}

	@NonNull
	@Override
	public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
				parent, false);
		return new ItemHolder(v);
	}

	public void deleteItem(int position) {
		getSnapshots().getSnapshot(position).getReference().delete();
	}

	class ItemHolder extends RecyclerView.ViewHolder {
		TextView textViewTitle;
		TextView textViewDescription;
		TextView textViewPriority;

		public ItemHolder(View itemView) {
			super(itemView);
			textViewTitle = itemView.findViewById(R.id.text_view_title);
			textViewDescription = itemView.findViewById(R.id.text_view_description);
			textViewPriority = itemView.findViewById(R.id.text_view_priority);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = getAdapterPosition();
					if (position != RecyclerView.NO_POSITION && listener != null) {
						listener.onItemClick(getSnapshots().getSnapshot(position), position);
					}
				}
			});
		}
	}

	public interface OnItemClickListener {
		void onItemClick(DocumentSnapshot documentSnapshot, int position);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}
}