package com.ajwalker.gui.league_gui;

import com.ajwalker.controller.TeamController;
import com.ajwalker.entity.League;
import com.ajwalker.entity.Team;
import com.ajwalker.model.LeagueModel;


import java.util.List;

import static com.ajwalker.utility.ConsoleTextUtils.*;

public class DetailedLeagueMenu {
    private League league;
    private TeamController teamController = TeamController.getInstance();





    public void detailedLeagueMenu(League league){
        boolean opt;
        this.league=league;
        do{
           opt = leagueInfoMenu();
        } while(opt);
    }




    private boolean leagueInfoMenu() {
        printTitle(league.getLeaugeName()+" menu");
        printMenuOptions("Show all fixture","Show weekly fixture","Show standings","Show played matches"
                ,"Return to Main Menu");
        return leagueInfoMenuOptions(getIntUserInput("Select: "));
    }

    private boolean leagueInfoMenuOptions(int userInput) {
        switch (userInput) {
            case 1:
                new LeagueModel(league).displayFixture();
                break;
            case 2:
                displayWeeklyFixture();
                break;
            case 3:
                new LeagueModel(league).displayStandings();
                break;
            case 4:
                break;
            case 5:
                return false;


        }
        return true;
    }
    private void displayWeeklyFixture(){
        int answer = getIntUserInput("Which week would you like to display: ");
        List<Team> teamsByLeague = teamController.findTeamsByLeague(league);
        if(answer>0 || answer<=(teamsByLeague.size()-1)*2){
            new LeagueModel(league).displayWeeklyFixture(answer);
            return;
        }
        System.out.println("Invalid choice...");
    }



}