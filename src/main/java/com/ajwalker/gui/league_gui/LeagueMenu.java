package com.ajwalker.gui.league_gui;

import com.ajwalker.controller.LeagueController;
import com.ajwalker.controller.TeamController;
import com.ajwalker.entity.League;
import com.ajwalker.entity.Manager;
import com.ajwalker.entity.Team;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.ajwalker.utility.ConsoleTextUtils.*;


public class LeagueMenu {
    private Optional<Manager> manager = Optional.empty();

    private final int starSize = 50;
    private LeagueController leagueController = LeagueController.getInstance();
    private TeamController teamController = TeamController.getInstance();

    private static LeagueMenu instance;
    private LeagueMenu(){

    }
    public static LeagueMenu getInstance(){
        if(instance == null){
            instance = new LeagueMenu();
        }
        return instance;
    }



    public void leagueMenu(){
        boolean opt = true;
        do{
            if (manager.isEmpty()) opt = anonymousLeagueMenu();
                //-->             <--//
//            else opt = leagueMainMenu();
        } while(opt);
    }

    private boolean anonymousLeagueMenu() {
        printTitle(starSize,"LEAGUE MENU");
        printMenuOptions(starSize,"Show all leagues","Show all teams","Select League","Return to Main Menu");
        return anonymousLeagueMenuOptions(getIntUserInput("Select: "));


    }

    private boolean anonymousLeagueMenuOptions(int userInput) {
        switch(userInput){
            case 1:
                displayAllLeagues();
                break;
            case 2:
                displayAllTeams();
                break;

            case 3:
                League league = selectLeague();
                if(league != null){
                    new DetailedLeagueMenu().detailedLeagueMenu(league);
                }
                break;

            case 4:
                System.out.println("Returning to Main Menu");
                return false;
        }
        return true;
    }


    private League selectLeague() {
        displayAllLeagues();
        int choice = getIntUserInput("Select League: ");
        if(choice >0 && choice<=leagueController.findAll().size()){
            return leagueController.findAll().get(choice-1);
        }
        printErrorMessage("Invalid choice!");
        return null;
    }

    private void displayAllTeams() {
        AtomicInteger counter = new AtomicInteger(1);
        printTitle(starSize,"ALL TEAMS");
        List<Team> allTeams = teamController.findAll();
        Map<League,List<Team>> map = new HashMap<>();
        for(Team team : allTeams){
            if(!map.containsKey(team.getLeague())){
                map.putIfAbsent(team.getLeague(),new ArrayList<>());
                map.get(team.getLeague()).add(team);
            }
            else{
                map.get(team.getLeague()).add(team);
            }
        }
        map.forEach((k,v)->{
            System.out.println(k.getLeaugeName());
            for(Team team : v){
                if(!team.getTeamName().equalsIgnoreCase("bay")){
                    System.out.println(counter.getAndIncrement()+" - "+team.getTeamName());
                }

            }
            counter.set(1);
        });

    }

    private void displayAllLeagues(){
        AtomicInteger counter = new AtomicInteger(1);
        leagueController.findAll().forEach(l->{
            System.out.println(counter.getAndIncrement()+": "+l.getLeaugeName()+" - "+l.getSeason().getTitle());
        });
    }


}