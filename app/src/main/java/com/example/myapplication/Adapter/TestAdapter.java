package com.example.myapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DAO.TestDAO;
import com.example.myapplication.DTO.TestDTO;
import com.example.myapplication.CreateNotify;
import com.example.myapplication.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private final ArrayList<TestDTO> listTest;
    private final Context context;
    TestDTO testDTO;

    public TestAdapter(Context context, ArrayList<TestDTO> listTest) {
        this.context = context;
        this.listTest = listTest;
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        int pos = position;
        testDTO = listTest.get(position);
        holder.txtRoomName.setText("Phòng thi: " + testDTO.getTest_room());
        holder.txtRomeTime.setText("Ca thi: " + testDTO.getTest_shift());
        holder.txtRoomDate.setText("Ngày Thi: " + testDTO.getDate_test());
        holder.txtRoomSubject.setText("Môn Thi: " + testDTO.getTest_subject());

        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //define item
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có muốn xóa vĩnh viên không?");
                builder.setIcon(R.drawable.baseline_delete_forever_24);
                builder.setPositiveButton("Có", (dialog, which) -> {
                    TestDAO testDAO = new TestDAO(context);
                    try {
                        int result = testDAO.deleteTest(testDTO);
                        if (result > 0) {
                            String title = "Đã xóa " + testDTO.getTest_subject() + " - " + testDTO.getTest_room();
                            String content = "Ngày thi: " + testDTO.getDate_test() + " - Ca thi: " + testDTO.getTest_shift();
                            int id = testDTO.getId();
                            listTest.remove(pos);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();

                            CreateNotify notify = new CreateNotify();
                            notify.postNotification(context, title, content, id);
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                    dialog.dismiss();
                });
                builder.setNegativeButton("Không", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.show();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return listTest.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtRoomName, txtRomeTime, txtRoomDate, txtRoomSubject;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRoomName = itemView.findViewById(R.id.txt_room);
            txtRomeTime = itemView.findViewById(R.id.txt_time);
            txtRoomDate = itemView.findViewById(R.id.txt_date);
            txtRoomSubject = itemView.findViewById(R.id.txt_test_subject);
            constraintLayout = itemView.findViewById(R.id.cl_main);
        }
    }
}
