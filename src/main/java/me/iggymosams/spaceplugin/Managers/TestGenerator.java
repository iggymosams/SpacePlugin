package me.iggymosams.spaceplugin.Managers;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class TestGenerator extends ChunkGenerator {

    PerlinNoiseGenerator perlinNoiseGenerator;

    int currentHeight = 50;

    public TestGenerator(long seed){
        perlinNoiseGenerator = new PerlinNoiseGenerator(seed);
        perlinNoiseGenerator.noise(3,3,3);
    }
    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        // Remember this

        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.005D);

        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                currentHeight = (int) (generator.noise(x*16+X, z*16+Z, 0.5D, 1.5D)*15D+50D);
                chunk.setBlock(X, currentHeight, Z, Material.END_STONE);
                chunk.setBlock(X, currentHeight-1, Z, Material.END_STONE);
                for (int i = currentHeight-2; i > 0; i--)
                    chunk.setBlock(X, i, Z, Material.END_STONE);
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        if (random.nextInt(100) < 10) {
            int chunkX = source.getX();
            int chunkZ = source.getZ();

            int randomX = chunkX * 16 + random.nextInt(16);
            int randomZ = chunkZ * 16 + random.nextInt(16);
            int y = 0;

            for (y = world.getMaxHeight() - 1; world.getBlockAt(randomX, y, randomZ).getType() == Material.AIR; y--) ;
            y -= 7;

            Block block = world.getBlockAt(randomX + 8, y, randomZ + 8);

            if (random.nextInt(100) < 90) {
                block.setType(Material.WATER);
            } else {
                block.setType(Material.LAVA);
            }

            boolean[] booleans = new boolean[2048];

            int i = random.nextInt(4) + 4;

            int j, j1, k1;

            for (j = 0; j < i; ++j) {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int k = 1; k < 15; ++k) {
                    for (int l = 1; l < 15; ++l) {
                        for (int i1 = 0; i1 < 7; ++i1) {
                            double d6 = (k - d3) / (d0 / 2.0D);
                            double d7 = (i1 - d4) / (d1 / 2.0D);
                            double d8 = (l - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D) {
                                booleans[(k * 16 + l) * 8 + i1] = true;
                            }
                        }
                    }
                }
            }

            for (j = 0; j < 16; ++j) {
                for (k1 = 0; k1 < 16; ++k1) {
                    for (j1 = 0; j1 < 8; ++j1) {
                        if (booleans[(j * 16 + k1) * 8 + j1]) {
                            world.getBlockAt(randomX + j, y + j1, randomZ + k1).setType(j1 > 4 ? Material.AIR : block.getType());
                        }
                    }
                }
            }

            for (j = 0; j < 16; ++j) {
                for (k1 = 0; k1 < 16; ++k1) {
                    for (j1 = 4; j1 < 8; ++j1) {
                        if (booleans[(j * 16 + k1) * 8 + j1]) {
                            int X1 = randomX + j;
                            int Y1 = y + j1 - 1;
                            int Z1 = randomZ + k1;

                            if (world.getBlockAt(X1, Y1, Z1).getType() == Material.DIRT) {
                                world.getBlockAt(X1, Y1, Z1).setType(Material.GRASS);
                            }
                        }
                    }
                }
            }
        }
        return chunk;
    }



}

