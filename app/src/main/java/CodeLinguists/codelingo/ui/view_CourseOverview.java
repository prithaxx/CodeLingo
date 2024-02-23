
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		activity_course_overview
	 *	@date 		Wednesday 07th of February 2024 06:38:24 PM
	 *	@title 		Page 1
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */
	

package CodeLinguists.codelingo.ui;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;

    public class view_CourseOverview extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_overview);

		ISessionManager sessionManager = SessionManager.newInstance();

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.fragmentContainerView3, cont_CourseOverview.newInstance()).commit();
	}

	public void btnHamburgerMenuOnClick(View v){
		NavigationView navView = findViewById(R.id.nav_view);
		navView.setVisibility(View.VISIBLE);
	}
}
	
	