package tile;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    public int tileSize;
    int maxWorldCol;
    int maxWorldRow;
    public ArrayList<MapObject> mapObjects = new ArrayList<>();
    public TileManager(GamePanel gp) {
        this(gp, gp.tileSize, gp.maxWorldCol, gp.maxWorldRow);
        loadMap("/resources/maps/world01.txt");
    }

    public TileManager(GamePanel gp, int tileSize, int maxWorldCol, int maxWorldRow) {
        this.gp = gp;
        this.tileSize = tileSize;
        this.maxWorldCol = maxWorldCol;
        this.maxWorldRow = maxWorldRow;

        tile = new Tile[30];
        mapTileNum = new int[maxWorldCol][maxWorldRow];
        getTileImage();
        loadMapObjects();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/grassM.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/asset4.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/water2.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/brick.png")));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/treeN.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/road00.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/PitchRoad.png")));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/road_up.png")));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/road_down.png")));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/road_left.png")));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/road_right.png")));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/Sunflower.png")));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/Water3.png")));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/Water4.png")));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/Bush.png")));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/Bush0.png")));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/Banana.png")));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/BushS.png")));
            tile[17].collision = true;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/Mustard.png")));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/location.png")));
            tile[19].collision = true;
  
        }
        catch (IOException e){

        }
    }

    public void loadMapObjects() {
        try {
            mapObjects.add(new MapObject(49, 75 , ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/AC1.png")))));
            //mapObjects.add(new MapObject(61, 51, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/AC1Sign.png")))));
            mapObjects.add(new MapObject(48, 66, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/AC2.png")))));
            //mapObjects.add(new MapObject(50, 51, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/AC2Sign.png")))));
            mapObjects.add(new MapObject(34, 90, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/SM.png")))));
            mapObjects.add(new MapObject(48, 84, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Audit.png")))));
            mapObjects.add(new MapObject(88, 55, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Mondir.png")))));
            mapObjects.add(new MapObject(48, 45, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Mosjid.png")))));
            mapObjects.add(new MapObject(31, 88, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Mosjid2.png")))));

            mapObjects.add(new MapObject(36, 79, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Admin.png")))));
            mapObjects.add(new MapObject(35, 51, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Library.png")))));
            mapObjects.add(new MapObject(71, 86, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Bncc.png")))));
            mapObjects.add(new MapObject(31, 27, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Provost.png")))));
            mapObjects.add(new MapObject(60, 28, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Stuff1.png")))));
            mapObjects.add(new MapObject(38, 83, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/GCpp.png")))));
            mapObjects.add(new MapObject(36, 57, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/AC3.png")))));
            mapObjects.add(new MapObject(42, 93, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/WhiteBus.png")))));
            mapObjects.add(new MapObject(42, 93, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/WhiteBus.png")))));
            mapObjects.add(new MapObject(48, 93, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/RedBus.png")))));
            mapObjects.add(new MapObject(54, 93, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/RedBus.png")))));

            mapObjects.add(new MapObject(39, 28, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/WhiteBus.png")))));
            mapObjects.add(new MapObject(43, 29, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/RedBus.png")))));
            mapObjects.add(new MapObject(49, 28, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/WhiteBus.png")))));
            mapObjects.add(new MapObject(48, 31, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/RedBus.png")))));
            mapObjects.add(new MapObject(53, 30, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/WhiteBus2.png")))));

            mapObjects.add(new MapObject(51, 94, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/WhiteBus2.png")))));
            mapObjects.add(new MapObject(86, 78, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Salam.png")))));
            mapObjects.add(new MapObject(86, 59, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Salam.png")))));
            mapObjects.add(new MapObject(86, 68, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Malek.png")))));
            mapObjects.add(new MapObject(39, 89, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/location.png")))));
            mapObjects.add(new MapObject(47, 86, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/location.png")))));
            mapObjects.add(new MapObject(40, 32, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/location.png")))));
            mapObjects.add(new MapObject(40 ,52, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/location.png")))));
            mapObjects.add(new MapObject(6 , 59, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Teachers.png")))));
            mapObjects.add(new MapObject(38, 31, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/RedBus.png")))));
            mapObjects.add(new MapObject(46 ,31, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/location.png")))));
            mapObjects.add(new MapObject(61 ,48, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/location.png")))));
            mapObjects.add(new MapObject(60 ,48, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Vista.png")))));
            int i,j,k;
            for (i = 65 ; i<= 85 ; i++ )
            {
                mapObjects.add(new MapObject(i ,47, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tree2.png")))));
            }

            for (i = 32 ; i<= 55 ; i+=2 )
            {
                mapObjects.add(new MapObject(i ,35, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tree2.png")))));
            }

            for (i=15;i<=70;i++)
            {
                    mapObjects.add(new MapObject(i ,25, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tree2.png")))));
            }

            mapObjects.add(new MapObject(66 ,47, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Tong2.png")))));
            mapObjects.add(new MapObject(70 ,47, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Tong1.png")))));
            mapObjects.add(new MapObject(74 ,49, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Tong3.png")))));
            mapObjects.add(new MapObject(71 ,50, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/Bench.png")))));
            mapObjects.add(new MapObject(48 ,77, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/location.png")))));
            mapObjects.add(new MapObject(43 ,91, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/ATM.png")))));
            mapObjects.add(new MapObject(21 ,33, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/GirlHall1.png")))));
            mapObjects.add(new MapObject(15 ,33, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/GirlHall2.png")))));
            mapObjects.add(new MapObject(59 ,42, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/medical.png")))));
            mapObjects.add(new MapObject(32 ,43, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/WaterPlant.png")))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            String line ;
            while ((line = br.readLine()) != null && row < maxWorldRow) {
                String[] numbers = line.split(" ");

                for (col = 0; col < maxWorldCol; col++) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }

//            if (col == maxWorldCol){
//               col = 0 ;
//               row++;
//            }
            br.close();
        }
        catch (Exception e){

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < maxWorldCol && worldRow < maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * tileSize;
            int worldY = worldRow * tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (    worldX + tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY,tileSize, tileSize, null);
            }
            worldCol++;
            if (worldCol == maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

        for (int i=0;i<mapObjects.size();i++) {
            MapObject obj = mapObjects.get(i);
            int worldX = obj.getX() * tileSize;
            int worldY = obj.getY() * tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            g2.drawImage(obj.getImage(), screenX, screenY, null);
        }
    }

    public void drawFixed(Graphics2D g2) {
        for (int row = 0; row < maxWorldRow; row++) {
            for (int col = 0; col < maxWorldCol; col++) {
                int tileNum = mapTileNum[col][row];
                int x = col * tileSize;
                int y = row * tileSize;
                g2.drawImage(tile[tileNum].image, x, y, tileSize, tileSize, null);
            }
        }
    }
}
