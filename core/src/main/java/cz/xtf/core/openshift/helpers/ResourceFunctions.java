package cz.xtf.core.openshift.helpers;

import io.fabric8.kubernetes.api.model.Pod;

import java.util.List;
import java.util.function.Function;

public class ResourceFunctions {

	public static Function<List<Pod>, Boolean> areExactlyNPodsReady(int n) {
		return pods -> pods.size() == n && pods.stream().allMatch(ResourceParsers::isPodReady);
	}

	public static Function<List<Pod>, Boolean> areExactlyNPodsRunning(int n) {
		return pods -> pods.size() == n && pods.stream().allMatch(ResourceParsers::isPodRunning);
	}

	public static Function<List<Pod>, Boolean> haveAnyPodRestarted() {
		return haveAnyPodRestartedAtLeastNTimes(1);
	}

	public static Function<List<Pod>, Boolean> haveAnyPodRestartedAtLeastNTimes(int n) {
		return pods -> pods.stream().anyMatch(pod -> ResourceParsers.hasPodRestartedAtLeastNTimes(pod, n));
	}

	private ResourceFunctions() {

	}
}
