package andioopp.model.domain.player;

import andioopp.common.storage.ArrayListFactory;
import andioopp.model.domain.money.Money;
import andioopp.model.domain.money.Wallet;
import andioopp.model.domain.tower.TowerFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void giveMoneyGivesMoney() {
        Money money = new Money(7);
        Player player = new Player(new ArrayListFactory().create());
        player.giveMoney(money);
        assertEquals(money, player.getMoney());
    }

    @Test
    public void buyingRemovesMoney() {
        Player player = new Player(new ArrayListFactory().create(), new Wallet(new Money(10)));
        TowerCard<?> towerCard = new TowerCard<>(new Money(7), TowerFactory::createBobomb);
        player.buy(towerCard);
        assertEquals(new Money(3), player.getMoney());
    }

}
