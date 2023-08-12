package com.example.myapplication.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Adapter.TestAdapter;
import com.example.myapplication.CreateService;
import com.example.myapplication.DAO.TestDAO;
import com.example.myapplication.DTO.TestDTO;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    ArrayList<TestDTO> testDTOArrayList = new ArrayList<>();
    TestAdapter testAdapter;
    TestDTO testDTO;
    TestDAO testDAO;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        testDAO = new TestDAO(getContext());
        testDTOArrayList = testDAO.getAllTest();
        testAdapter = new TestAdapter(getContext(), testDTOArrayList);
        recyclerView.setAdapter(testAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.layout_add, null);
                EditText edtNameTest = view1.findViewById(R.id.edtName);
                EditText edtTimeTest = view1.findViewById(R.id.edtTimeTest);
                EditText edtRoomTest = view1.findViewById(R.id.edtRoomTest);
                EditText edtDateTest = view1.findViewById(R.id.edtDateTest);

                Button btnCanCel = view1.findViewById(R.id.btnCancel);
                Button btnAdd = view1.findViewById(R.id.btnAdd);

                builder.setView(view1);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnCanCel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameTest = edtNameTest.getText().toString().trim();
                        String timeTest = edtTimeTest.getText().toString().trim();
                        String roomTest = edtRoomTest.getText().toString().trim();
                        String dateTest = edtDateTest.getText().toString().trim();

                        //validate
                        if (nameTest.isEmpty()) {
                            edtNameTest.setError("Không được để trống");
                            return;
                        }
                        if (timeTest.isEmpty()) {
                            edtTimeTest.setError("Không được để trống");
                            return;
                        }
                        try {
                            int time = Integer.parseInt(timeTest);
                            if (time < 1 || time > 6) {
                                edtTimeTest.setError("Nhập số từ 1 đến 6");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            edtTimeTest.setError("Nhập số");
                            return;
                        }

                        if (roomTest.isEmpty()) {
                            edtRoomTest.setError("Không được để trống");
                            return;
                        }
                        if (roomTest.length() != 4) {
                            edtRoomTest.setError("Nhập tối đa 4 kí tự");
                            return;
                        }
                        if (dateTest.isEmpty()) {
                            edtDateTest.setError("Không được để trống");
                            return;
                        }
                        //validate date using try catch
                        // Validate the date using try-catch
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                        try {
                            Date date = dateFormat.parse(dateTest);
                            testDTO = new TestDTO(dateTest, timeTest, roomTest, nameTest);

                        } catch (ParseException e) {
                            edtDateTest.setError("yyyy-MM-dd");
                        }

                        testDAO = new TestDAO(getContext());
                        try {
                            long result = testDAO.insertTest(testDTO);
                            if (result > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                getData();

                                alertDialog.dismiss();
                                Intent intent = new Intent(requireContext(), CreateService.class);
                                requireContext().startService(intent);
                                Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }


                });

            }
        });

    }

    private void getData() {
        testDTOArrayList.clear();
        testDAO = new TestDAO(getContext());
        testDTOArrayList.addAll(testDAO.getAllTest());
        testAdapter.notifyDataSetChanged();
    }
}