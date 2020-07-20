package icfpc2020.api;

import icfpc2020.DemodulateList;
import icfpc2020.MessageImpl;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameResponseTest {
    @Test
    public void testA() {
        GameResponse gameResponse =
                new GameResponse(DemodulateList.demMList(new MessageImpl("110110000111011000101100110000")));
        Assert.assertTrue(gameResponse.success);
        Assert.assertEquals(GameStage.FINISHED, gameResponse.gameStage);
        Assert.assertNull(gameResponse.staticGameInfo);
        Assert.assertNull(gameResponse.gameState);
        Assert.assertEquals(
                "GameResponse{success=true, gameStage=FINISHED, staticGameInfo=null, gameState=null}",
                gameResponse.toString());
    }

    @Test
    public void testB() {
        GameResponse gameResponse =
                new GameResponse(DemodulateList.demMList(new MessageImpl("110110000111010111101111000010000000011010111101111000100000000011011000011101110010000000011110111000010000110111010000000001111011000011101100010110110000111011000100000110000")));
        Assert.assertEquals(
                "GameResponse{success=true, gameStage=NOT_STARTED, staticGameInfo=StaticGameInfo{role=ATTACKER}, gameState=null}",
                gameResponse.toString());
    }

    @Test
    public void testC() {
        GameResponse gameResponse =
                new GameResponse(DemodulateList.demMList(new MessageImpl("11011000011101100010111101111000010000000011010111101111000100000000011011000011101110010000000011110111000010000110111010000000001111011000011101100010110110000111011000100000110000")));
        Assert.assertEquals(
                "GameResponse{success=true, gameStage=FINISHED, staticGameInfo=StaticGameInfo{role=ATTACKER}, gameState=null}",
                gameResponse.toString());
    }

    @Test
    public void testD() {
        GameResponse gameResponse =
                new GameResponse(DemodulateList.demMList(new MessageImpl("110110000111011000101111011110000100000000110110000111110111100001110000001101100001110111001000000001111011100001000011011101000000000110000110000")));
        Assert.assertEquals(
                "GameResponse{success=true, gameStage=FINISHED, staticGameInfo=StaticGameInfo{role=DEFENDER}, gameState=null}",
                gameResponse.toString());
    }
}
