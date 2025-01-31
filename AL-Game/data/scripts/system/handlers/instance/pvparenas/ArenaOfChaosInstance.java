/*
 * This file is part of aion-lightning <aion-lightning.org>
 *
 * aion-lightning is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-lightning is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-lightning. If not, see <http://www.gnu.org/licenses/>.
 */
package instance.pvparenas;

import java.util.List;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.sort;

import com.aionemu.gameserver.instance.handlers.InstanceID;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.instance.playerreward.InstancePlayerReward;
import com.aionemu.gameserver.model.instance.playerreward.PvPArenaPlayerReward;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 *
 * @author xTz
 */
@InstanceID(300350000)
public class ArenaOfChaosInstance extends ChaosTrainingGroundsInstance {

	@Override
	protected boolean isPvpArena(){
		return true;
	}
	
	@Override
	protected void reward() {
		/*int totalPoints = instanceReward.getTotalPoints();
		int size = instanceReward.getInstanceRewards().size();*/
		// 100 * (rate * size) * (playerScore / playersScore)
		/*float totalScoreAP = (3.292f * size) * 100;
		float totalScoreCrucible = (1.964f * size) * 100;
		float totalScoreCourage = (0.225f * size) * 100;*/
		// to do other formula
		//float rankingRate = 0;
		/*if (size > 1) {
			rankingRate = (0.077f * (8 - size));
		}*/
		/*float totalRankingAP = 1453 - 1453 * rankingRate;
		float totalRankingCrucible = 865 - 865 * rankingRate;
		float totalRankingCourage = 101 - 101 * rankingRate;*/
		int nbPlayer = instanceReward.getInstanceRewards().size();
		for (InstancePlayerReward playerReward : instanceReward.getInstanceRewards()) {
			PvPArenaPlayerReward reward = (PvPArenaPlayerReward) playerReward;
			if (!reward.isRewarded()) {
				/*float playerRate = 1;
				Player player = instance.getPlayer(playerReward.getOwner());
				if (player != null) {
					playerRate = player.getRates().getChaosRewardRate();
				}*/
				int score = reward.getScorePoints();
				//float scoreRate = ((float) score / (float) totalPoints);
				int rank = instanceReward.getRank(score);
				if(rank < 2){
					reward.setGoldMedal(1);
				}else{
					reward.setSilverMedal(1);
				}
				int minAp = 5000;
				int minCri = 30;
				int minCru = 500;
				int maxAp = 9000;
				int maxCri = 60;
				int maxCru = 900;

				int difAP = (maxAp - minAp)/nbPlayer;
				int difCri = (maxCri - minCri)/nbPlayer;
				int difCru = (maxCru - minCru)/nbPlayer;
				//AP
				/*float percent = reward.getParticipation();
				float generalRate = 0.167f + rank * 0.095f;
				int basicAP = 200;
				float rankingAP = totalRankingAP;
				if (rank > 0) {
					rankingAP = rankingAP - rankingAP * generalRate;
				}
				int scoreAP = (int) (totalScoreAP * scoreRate);
				basicAP *= percent;
				rankingAP *= percent;
				rankingAP *= playerRate;
				reward.setBasicAP(basicAP);
				reward.setRankingAP((int) rankingAP);
				reward.setScoreAP(scoreAP);*/
				reward.setBasicAP(0);
				reward.setRankingAP(0);
				reward.setScoreAP((int)(maxAp - difAP * rank));
				
				//insigne ordalie
				/*int basicCrI = 195;
				basicCrI *= percent;
				float rankingCrI = totalRankingCrucible;
				if (rank > 0) {
					rankingCrI = rankingCrI - rankingCrI * generalRate;
				}
				rankingCrI *= percent;
				rankingCrI *= playerRate;
				int scoreCrI = (int) (totalScoreCrucible * scoreRate);
				reward.setBasicCrucible(basicCrI);
				reward.setRankingCrucible((int) rankingCrI);
				reward.setScoreCrucible(scoreCrI);*/
				reward.setBasicCrucible(0);
				reward.setRankingCrucible(0);
				reward.setScoreCrucible((int)(maxCru - difCru * rank));
				
				//insigne courage
				/*int basicCoI = 0;
				basicCoI *= percent;
				float rankingCoI = totalRankingCourage;
				if (rank > 0) {
					rankingCoI = rankingCoI - rankingCoI * generalRate;
				}
				rankingCoI *= percent;
				rankingCoI *= playerRate;
				int scoreCoI = (int) (totalScoreCourage * scoreRate);
				reward.setBasicCourage(basicCoI);
				reward.setRankingCourage((int) rankingCoI);
				reward.setScoreCourage(scoreCoI);*/
				reward.setBasicCourage(0);
				reward.setRankingCourage(0);
				reward.setScoreCourage((int)(maxCri - difCri * rank));
			}
		}
		super.reward();
	}



	@Override
	protected boolean canStart() {
		boolean res = super.canStart();
		if(!res){
			return false;
		}

		int itemId = getIdEnteringItem();
		
		for(Player p : instance.getPlayersInside()){
			PacketSendUtility.sendMessage(p, "CAN START "+p.getName());
			List<Item> items = p.getInventory().getItemsByItemId(itemId);
			items = sort(items, on(Item.class).getExpireTime());
			for (Item item : items) {
				ticketRemoved.put(p.getObjectId(), 1);
				p.getInventory().decreaseItemCount(item, 1);
				break;
			}
		}
		return true;
	}

	@Override
	protected int getIdEnteringItem() {
		return 186000135;
	}

}
