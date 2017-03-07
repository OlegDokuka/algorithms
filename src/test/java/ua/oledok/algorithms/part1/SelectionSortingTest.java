package ua.oledok.algorithms.part1;


import edu.princeton.cs.algs4.StdRandom;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Sorting.class)
public class SelectionSortingTest {

    @Test
    public void shouldSort() throws Exception {
        int[] actual = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] shuffled = Arrays.copyOf(actual, 10);

        StdRandom.shuffle(shuffled);
        ArgumentCaptor<?> lessCapture = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<?> exchangeCapture = ArgumentCaptor.forClass(Integer.class);
        PowerMockito.mockStatic(Sorting.class);
        PowerMockito.when(Sorting.class, "selection", any())
                .thenCallRealMethod();
        PowerMockito.when(Sorting.class, "exchange", any(int[].class), exchangeCapture.capture(), anyInt())
                .thenCallRealMethod();
        PowerMockito.when(Sorting.class, "less", lessCapture.capture(), any())
                .thenCallRealMethod();

        Sorting.selection(shuffled);

        assertArrayEquals(actual, shuffled);
        assertThat(exchangeCapture.getAllValues().size(), Matchers.lessThanOrEqualTo(10));
        assertThat(lessCapture.getAllValues().size(), Matchers.equalTo((shuffled.length * (shuffled.length - 1) / 2)));
    }

    @Test
    public void shouldSortWorstCase() throws Exception {
        int[] actual = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] shuffled = {10, 9, 8, 7, 6, 3, 2, 1, 5, 4};

        ArgumentCaptor<?> lessCapture = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<?> exchangeCapture = ArgumentCaptor.forClass(Integer.class);
        PowerMockito.mockStatic(Sorting.class);
        PowerMockito.when(Sorting.class, "selection", any())
                .thenCallRealMethod();
        PowerMockito.when(Sorting.class, "exchange", any(int[].class), exchangeCapture.capture(), anyInt())
                .thenCallRealMethod();
        PowerMockito.when(Sorting.class, "less", lessCapture.capture(), any())
                .thenCallRealMethod();

        Sorting.selection(shuffled);

        assertArrayEquals(actual, shuffled);
        assertThat(exchangeCapture.getAllValues().size(), Matchers.equalTo(9));
        assertThat(lessCapture.getAllValues().size(), Matchers.equalTo((shuffled.length * (shuffled.length - 1) / 2)));
    }

    @Test
    public void shouldSortAlreadySortedCase() throws Exception {
        int[] actual = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] shuffled = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        ArgumentCaptor<?> lessCapture = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<?> exchangeCapture = ArgumentCaptor.forClass(Integer.class);
        PowerMockito.mockStatic(Sorting.class);
        PowerMockito.when(Sorting.class, "selection", any())
                .thenCallRealMethod();
        PowerMockito.when(Sorting.class, "exchange", any(int[].class), exchangeCapture.capture(), anyInt())
                .thenCallRealMethod();
        PowerMockito.when(Sorting.class, "less", lessCapture.capture(), any())
                .thenCallRealMethod();

        Sorting.selection(shuffled);

        assertArrayEquals(actual, shuffled);
        assertThat(exchangeCapture.getAllValues().size(), Matchers.equalTo(0));
        assertThat(lessCapture.getAllValues().size(), Matchers.equalTo((shuffled.length * (shuffled.length - 1) / 2)));
    }
}
