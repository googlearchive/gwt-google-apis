# Workflow for Accepting Patches #

The Google API Library for GWT project is open source with an open development policy.

  * Patches are welcome from anyone in the community.  If you are not a regular contributor, it will be most helpful to post your patch as an [issue](http://code.google.com/p/gwt-google-apis/issues/list) and follow up with a message to the [gwt-google-apis group](https://groups.google.com/group/gwt-google-apis).

  * All patches should be peer reviewed before committed to the repository.  Reviews are a public process and should be posted to the rietveld instance at [gwt-code-reviews.appspot.com](http://gwt-code-reviews.appspot.com).

  * When creating a review, please add `galgwt-issue-notifications@googlegroups.com` to the CC line.  This is a public group reserved for patches, changes to the issue tracker, and commit messages for the group.

  * Patches should not be committed until a reviewer gives a "Looks Good" message (LGTM).

  * All commit messages should note the name of the reviewer and the URL of the review:

```
Updates the Analytics feature in Gadgets

Review by: piotrs
http://gwt-code-reviews.appspot.com/629801/show
```

  * In general, you should only make commits to trunk.  Library maintainers will pull patches back to the release branches when preparing a release and will update the branch-info.txt files appropriately.


# Guidelines for Reviewers #

  * Make sure new patches are consistent with the [Design Goals](APIDesignGoals.md) of the API bindings.

  * Patches should follow the [GWT code style guidelines](http://code.google.com/webtoolkit/makinggwtbetter.html).  A good way to help enforce this style is to use Eclipse configured with the GWT formatting setup and Checkstyle plugin.

  * API calls should first be deprecated for an appreciable period of time before being removed.  Users need a chance to transition their code.  If you have a question on how to effectively do this, please ask on the [gwt-google-apis group](https://groups.google.com/group/gwt-google-apis).

  * Make sure that new functionality is accompanied by unit tests or a manual test plan for testing.