package com.ajwalker.service;

import com.ajwalker.entity.BetOdds;
import com.ajwalker.entity.Match;
import com.ajwalker.repository.BetOddsRepository;
import com.ajwalker.repository.ICRUD;

import java.util.ArrayList;
import java.util.List;

public class BetOddsService extends ServiceManager<BetOdds,Long> {
    private static BetOddsService instance;
    private BetOddsRepository betOddsRepository = BetOddsRepository.getInstance();

    private BetOddsService() {
        super(BetOddsRepository.getInstance());
    }
    public static BetOddsService getInstance() {
        if (instance == null) {
            instance = new BetOddsService();
        }
        return instance;
    }

    public List<BetOdds> getBetOddsListByMatches(List<Match> matches) {
        List<BetOdds> betOddsList = new ArrayList<>();
            for(Match match : matches) {
                List<BetOdds> match1 = betOddsRepository.findByFieldNameAndValue("match", match);
                betOddsList.addAll(match1);
            }
            return betOddsList;
    }

}