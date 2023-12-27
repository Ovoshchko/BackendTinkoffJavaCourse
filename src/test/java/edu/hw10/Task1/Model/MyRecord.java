package edu.hw10.Task1.Model;

import edu.hw10.Task1.Annotations.Min;

public record MyRecord(@Min(0) int anInt) {
}
