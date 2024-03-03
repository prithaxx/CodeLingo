
	 
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

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import CodeLinguists.codelingo.R;
import CodeLinguists.codelingo.application.Services;
import CodeLinguists.codelingo.logic.ISessionManager;
import CodeLinguists.codelingo.logic.SessionManager;

    public class view_CourseOverview extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_overview);

		ISessionManager sessionManager = Services.getSessionManager();

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.fragmentContainerView3, cont_CourseOverview.newInstance()).commit();
	}

	public void btnHamburgerMenuOnClick(View v){
		NavigationView navView = findViewById(R.id.nav_view);
		navView.setVisibility(View.VISIBLE);
	}

	public void btnCloseMenuOnClick(View v){
		NavigationView navView = findViewById(R.id.nav_view);
		navView.setVisibility(View.INVISIBLE);
	}

	public void navigateToLogin(MenuItem item) {
		Intent intent = new Intent(view_CourseOverview.this, view_GuestLogin.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}
}
	
	