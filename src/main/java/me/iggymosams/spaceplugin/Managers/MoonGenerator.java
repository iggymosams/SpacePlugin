package me.iggymosams.spaceplugin.Managers;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Panda;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Random;

public class MoonGenerator extends ChunkGenerator {

    SimplexOctaveGenerator simplexOctaveGenerator;

    int currentHeight = 50;

    public MoonGenerator(long seed){
        simplexOctaveGenerator = new SimplexOctaveGenerator(seed, 4);
        simplexOctaveGenerator.setScale((double) 1 /100);
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        // Remember this

        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.005D);

        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                currentHeight = (int) (generator.noise(x*16+X, z*16+Z, 0.5D, 0.5D)*15D+50D);
                chunk.setBlock(X, currentHeight, Z, Material.END_STONE);
                chunk.setBlock(X, currentHeight-1, Z, Material.END_STONE);
                for (int i = currentHeight-2; i > 0; i--)
                    chunk.setBlock(X, i, Z, Material.END_STONE);
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        return chunk;
    }
}

