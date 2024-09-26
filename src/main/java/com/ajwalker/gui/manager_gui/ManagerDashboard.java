package com.ajwalker.gui.manager_gui;

import com.ajwalker.controller.ManagerController;
import com.ajwalker.entity.Manager;
import com.ajwalker.entity.Player;
import com.ajwalker.model.TeamModel;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.ajwalker.utility.ConsoleTextUtils.*;

public class ManagerDashboard {
	private Manager manager;
	ManagerController managerController = ManagerController.getInstance();
	private final int starSize = 50;
	
	
	
	public Optional<Manager> managerDashboard(Optional<Manager> manager) {
		AtomicBoolean opt = new AtomicBoolean(false);
		manager.ifPresentOrElse(m ->{
			this.manager = m;
			do {
				opt.set(managerDashboardMenu());
			} while (opt.get());
		} , () ->  this.manager=null);
		
		return Optional.ofNullable(this.manager);
	}
	
	private boolean managerDashboardMenu() {
		printTitle(starSize,"Manager Dashboard Menu");
		printMenuOptions(starSize,
		                 "Display My Team",
		                 "Manage Players",
						 "Manager Profile",
		                 "Logout",
		                 "Return To Main Menu");
		return managerDashboardMenuOptions(getIntUserInput("Select: "));
	}
	
	private boolean managerDashboardMenuOptions(int intUserInput) {
		switch (intUserInput) {
			case 1:{
				displayMyTeam();
				break;
			}
			case 2:{
				ManagePlayers managePlayers = new ManagePlayers();
				managePlayers.managePlayers(Optional.of(manager));
				break;
			}
			case 3:{
				break;
			}
			case 4:{ // Logout
				manager = null;
				System.out.println("Logging out...");
				return false;
				
			}
			case 5:{// Exit..
				return false;
			}
		}
		return true;
	}

	public void displayMyTeam(){
		new TeamModel(manager.getTeam()).displayMyTeam();
	}
	
}