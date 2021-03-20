package me.iggymosams.spaceplugin.Managers;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class MoonGenerator extends ChunkGenerator {

    public static MaterialGradient upperCavernMaterial;

    static {
        upperCavernMaterial = new MaterialGradient();
        upperCavernMaterial.nodes.add(new GradientNode(Material.STONE, 0.0, 0.0, 1.0, 1.0));
        upperCavernMaterial.nodes.add(new GradientNode(Material.BLACKSTONE, 0.1, 1.0, 1.0, 2.0));
        upperCavernMaterial.nodes.add(new GradientNode(Material.GILDED_BLACKSTONE, 0.2, 1.0, 1.0, 0.25));
        upperCavernMaterial.nodes.add(new GradientNode(Material.COAL_ORE, 0.0, 0.0, 0.2, 0.05));
        upperCavernMaterial.nodes.add(new GradientNode(Material.REDSTONE_ORE, 0.0, 1.0, 1.0, 0.025));
        upperCavernMaterial.nodes.add(new GradientNode(Material.LAPIS_ORE, 0.0, 1.0, 1.0, 0.025));
        upperCavernMaterial.nodes.add(new GradientNode(Material.DIAMOND_ORE, 0.2, 0.3, 1.0, 0.05));
        upperCavernMaterial.nodes.add(new GradientNode(Material.IRON_ORE, 0.0, 0.3, 1.0, 0.05));
        upperCavernMaterial.nodes.add(new GradientNode(Material.GOLD_ORE, 0.2, 1.0, 1.0, 0.05));
    }

    SimplexOctaveGenerator simplexOctaveGenerator;

    public MoonGenerator(long seed) {
        simplexOctaveGenerator = new SimplexOctaveGenerator(seed, 4);
        simplexOctaveGenerator.setScale((double) 1 / 100);
    }

    public static int getHeight(SimplexOctaveGenerator generator, double x, double z, double freq, double amp, double baseHeight) {
        return (int) ((generator.noise(x, z, freq, 0.5D, true) + 1) * amp + baseHeight);
    }

    public static SimplexOctaveGenerator getNoiseGenerator(World world) {
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(world, 8);
        generator.setScale(0.01D);

        return generator;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        SimplexOctaveGenerator generator = getNoiseGenerator(world);
        int blockX, blockZ, surfaceHeight, surfaceBottomHeight, cavernTopHeight, cavernBottomHeight;
        double noise, depth;

        for (int X = 0; X < 16; X++) {
            blockX = chunkX * 16 + X;
            for (int Z = 0; Z < 16; Z++) {
                blockZ = chunkZ * 16 + Z;

                surfaceHeight = getHeight(generator, blockX, blockZ, 1D, 30D, 64D);
                surfaceBottomHeight = getHeight(generator, blockX, blockZ, 0.5D, 5D, 0D);
                cavernTopHeight = getHeight(generator, blockX, blockZ, 10D, 20D, 50D);
                cavernBottomHeight = getHeight(generator, blockX, blockZ, 2D, 40D, 20D);

                // Set biome
                for (int y = 255; y >= surfaceBottomHeight; y--) {
                    biome.setBiome(X, y, Z, Biome.DESERT_HILLS);
                }

                // Set surface
                for (int y = surfaceHeight; y > surfaceHeight - 4; y--)
                    chunk.setBlock(X, y, Z, Material.END_STONE);

                // Set upper caverns
                for (int y = surfaceHeight - 4; y > 0; y--) {
                    if (y >= surfaceHeight - 6) {
                        chunk.setBlock(X, y, Z, Material.STONE);
                    } else {
                        noise = generator.noise(blockX / 2.0, y, blockZ / 2.0, 0.0, 1.375, 4.0, true);
                        if (noise >= 0.0) {
                            chunk.setBlock(X, y, Z,
                                    upperCavernMaterial.getMaterial(random, noise));
                        } else {
                            chunk.setBlock(X, y, Z, Material.CAVE_AIR);
                        }
                    }
                    // chunk.setBlock(X, y, Z,
                    //   upperCavernMaterial.getMaterial(random, NumberUtil.mix(0.0, noise, depth)));
                }


                // Set lava
                // for (int y = 40; y > 25; y--) {
                //   if(chunk.getBlockData(X, y, Z).getMaterial().isAir()) {
                //     chunk.setBlock(X, y, Z, Material.LAVA);
                //   }
                // }

                // Set bedrock cap
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        }

        return chunk;
    }

}

